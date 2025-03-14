package com.example.exercicio01.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vinhos")
public class Vinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome_do_vinho")
    private String nomeDoVinho;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoVinho tipo;

    @NotNull
    @Min(0)
    @Column(name = "safra")
    private int safra;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "preco")
    private BigDecimal preco;

    public enum TipoVinho {
        Tinto, Branco, Rosé
    }
}