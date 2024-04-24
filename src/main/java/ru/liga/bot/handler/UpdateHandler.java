package ru.liga.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.bot.type.BotCommandState;
import ru.liga.cargo.service.CargoService;
import ru.liga.truck.exception.TruckNumberExceededException;

@Component
public class UpdateHandler {
    private final CargoService cargoService;
    private BotCommandState processTxtToMessageState;

    @Autowired
    public UpdateHandler(CargoService cargoService) {
        this.cargoService = cargoService;
        this.processTxtToMessageState = BotCommandState.STARTED;
    }

    public SendMessage execute(Update update) throws TruckNumberExceededException {
        String command = update.getMessage().getText();

        if (command.equals("/start")) {
            return replyToStart(update.getMessage().getChatId());
        } else if (command.equals("/process_from_txt_to_message") || processTxtToMessageState.equals(BotCommandState.WAITING_INPUT)) {
            return replyToProcessTxtToMessage(update.getMessage().getChatId(), update.getMessage().getText());
        }

        return replyToDefault(update.getMessage().getChatId(), update.getMessage().getText());
    }

    public SendMessage replyToStart(long chatId) {
        SendMessage message = new SendMessage();

        message.setChatId(chatId);
        message.enableMarkdown(true);
        message.setText("Доступные команды: \n/processtxt\n/processjson");

        return message;
    }

    public SendMessage replyToDefault(long chatId, String updateMessage) {
        SendMessage message = new SendMessage();

        message.setChatId(chatId);
        message.enableMarkdown(true);
        message.setText("Некорректная команда: " + updateMessage);

        return message;
    }


    public SendMessage replyToProcessTxtToMessage(Long chatId, String update) throws TruckNumberExceededException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);

        if (processTxtToMessageState.equals(BotCommandState.WAITING_INPUT)) {
            String trucks = cargoService.processTxtToString(update);

            sendMessage.setText("```\n" + trucks + "```");

            processTxtToMessageState = BotCommandState.COMPLETED;

            return sendMessage;
        }

        processTxtToMessageState = BotCommandState.WAITING_INPUT;
        sendMessage.setText("Отправь список посылок в следующем формате:\n```\n999\n999\n999\n\n666\n666\n\n55555\n\n1\n\n1\n\n333\n```");
        return sendMessage;
    }
}
