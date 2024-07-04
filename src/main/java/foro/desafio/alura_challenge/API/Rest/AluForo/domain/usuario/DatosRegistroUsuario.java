package foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 8, max = 12, message = "La contraseña debe tener entre 8 y 12 caracteres")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", message = "La contrasela debe contener" +
                " al menos un número, una letra mayúscula y un caracter especial")
        String password
) {
}
