package com.ceiba.obra.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.obra.modelo.entidad.Obra;
import com.ceiba.obra.modelo.entidad.TipoObra;
import com.ceiba.obra.servicio.testdatabuilder.ObraTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ObraTest {

    @Test
    @DisplayName("Deberia crear correctamente la obra")
    void deberiaCrearCorrectamenteLaObra() {
        // arrange
        LocalDateTime fechaCreacion = LocalDateTime.now();
        //act
        Obra obra = new ObraTestDataBuilder().conId(1L).build();
        //assert
        assertEquals(1, obra.getId());
        assertEquals("La Gioconda", obra.getTitulo());
        assertEquals(TipoObra.REALISMO, obra.getTipoObra());
        assertEquals(3400000L, obra.getPrecio());
        assertFalse(obra.getVendido());
    }

    @Test
    void deberiaFallarSinNombreDeUsuario() {

        //Arrange
        ObraTestDataBuilder usuarioTestDataBuilder = new ObraTestDataBuilder().conTitulo(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el titulo de la obra");
    }

    @Test
    void deberiaFallarSinClave() {

        //Arrange
        ObraTestDataBuilder usuarioTestDataBuilder = new ObraTestDataBuilder().conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la clave");
    }

    @Test
    void deberiaFallarSinTamanioClave() {

        //Arrange
        ObraTestDataBuilder usuarioTestDataBuilder = new ObraTestDataBuilder().conClave("123").conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "La clave debe tener una longitud mayor o igual a 4");
    }

    @Test
    void deberiaFallarSinFechaCreacion() {

        //Arrange
        ObraTestDataBuilder usuarioTestDataBuilder = new ObraTestDataBuilder().conFechaCreacion(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de creación");
    }


}