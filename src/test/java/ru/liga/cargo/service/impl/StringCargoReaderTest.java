package ru.liga.cargo.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.cargo.Cargo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class StringCargoReaderTest {
    @Test
    public void testReadMethod_CorrectInput_CorrectOutput() {
        String rawCargos = "1\n\n22\n\n333\n\n4444";

        StringCargoReader stringCargoReader = new StringCargoReader();
        List<Cargo> cargos = stringCargoReader.read(rawCargos);

        assertThat(cargos).hasSize(4);

        assertThat(cargos.get(0).getVolume()).isEqualTo(1);
        assertThat(cargos.get(1).getVolume()).isEqualTo(2);
        assertThat(cargos.get(2).getVolume()).isEqualTo(3);
        assertThat(cargos.get(3).getVolume()).isEqualTo(4);

    }
}