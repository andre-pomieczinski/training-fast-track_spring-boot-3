package br.com.alura.spring.data.orm;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nome;
    private String cpf;
    private Double salario;
    private LocalDate dataContratacao;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Override
    public String toString() {
        return "Funcionario{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                '}';
    }

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionarios_unidades",
               joinColumns = {@JoinColumn(name = "fk_funcionario")},
               inverseJoinColumns = {@JoinColumn(name = "fk_unidade")}
    )
    private List<UnidadeTrabalho> unidadeTrabalhos;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<UnidadeTrabalho> getUnidadeTrabalhos() {
        return unidadeTrabalhos;
    }

    public void setUnidadeTrabalhos(List<UnidadeTrabalho> unidadeTrabalhos) {
        this.unidadeTrabalhos = unidadeTrabalhos;
    }
}
