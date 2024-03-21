# Simple-Spring-Batch-Template
Uma simples template de **Spring Batch**.

## Pré-requisitos
- Java 17 ou posterior
- Gradle (ou use o script ./gradlew incluído)

## Descrição
Neste projeto há um simples Job que executa os seguintes passos (Step):
1. Ler dados de um arquivo CSV no seguinte caminho:
<pre>src/main/resources/data.csv</pre>
3. Com os dados, cria instâncias da entidade <code>User</code>
4. Persistir as entidades no banco de dados

Este Job é apenas uma _amostra_ do que é possível desenvolver com Spring Boot + Spring Batch.
