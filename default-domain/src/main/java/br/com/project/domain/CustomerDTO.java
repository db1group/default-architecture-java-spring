package br.com.project.domain;

import java.util.Objects;

public class CustomerDTO {

    private Long id;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public CustomerDTO() {
        // Frameworks
    }

    private CustomerDTO(CustomerBuilder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
    }

    public static class CustomerBuilder {
        private Long id;
        private String nome;

        public CustomerBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public CustomerDTO build() {
            return new CustomerDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO customerDTO = (CustomerDTO) o;
        return Objects.equals(id, customerDTO.id) &&
                Objects.equals(nome, customerDTO.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
