package ru.liga.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class Config {
    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String botToken;

//    @Bean
//    public TelegramBotsApi telegramBotsApi(TelegramBot telegramBot) throws TelegramApiException {
//        var api = new TelegramBotsApi(DefaultBotSession.class);
//        api.registerBot(telegramBot);
//        return api;
//    }
}
