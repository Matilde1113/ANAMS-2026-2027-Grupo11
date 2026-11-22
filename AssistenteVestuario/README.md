# Assistente Digital de Vestuário

Trabalho prático de Análise e Modelação de Sistemas (2026/2027) – Grupo 11, Iteração 2.

Aplicação de consola em Java que permite a cada utilizador gerir o seu guarda-roupa virtual
e receber sugestões de outfits adequadas à ocasião, ao tempo e às avaliações que já fez.

## Como executar

1. Abrir a pasta do projeto no IntelliJ (projeto Maven, Java 17 ou superior).
2. Executar a classe `org.UI.Main`.
3. São carregados dados de teste automaticamente:

| Perfil        | Email              | Password  |
|---------------|--------------------|-----------|
| Administrador | admin@av.pt        | admin123  |
| Utilizador    | utilizador@av.pt   | teste123  |

## Arquitetura

O código segue o padrão **UI → Controller → Model** e os padrões GRASP descritos no relatório:

- **Controller** – uma classe Controller por caso de uso (ex.: `AdicionarPecaController`).
- **Creator / Information Expert** – ex.: o `GuardaRoupa` cria e guarda as peças; a `Sugestao` cria a sua `Avaliacao`.
- **Pure Fabrication / Baixo Acoplamento / Alta Coesão** – as listas foram promovidas a classes `Registo…`
  (`RegistoUtilizadores`, `RegistoEstilos`, `RegistoCores`, `RegistoMateriais`, `RegistoCategorias`,
  `RegistoOcasioes`, `RegistoPartesCorpo`, `RegistoTamanhos`, `RegistoEstados`).
- **Protected Variations** – o algoritmo de sugestões está atrás da interface `IMotorSugestoes`,
  implementada por `MotorSugestoesRegras`.
- **Herança e polimorfismo** – `Tamanho` (abstrata) com `TamanhoNumerico` e `TamanhoAlfabetico`.
- **Segurança** – as passwords são guardadas cifradas (SHA-256).

## Casos de uso implementados

| UC   | Descrição                                     | Classes (UI / Controller)                     | Responsável |
|------|-----------------------------------------------|-----------------------------------------------|-------------|
| UC1  | Registar Utilizador                           | `RegistarUtilizadorUI` / `…Controller`        | Carolina    |
| UC2  | Especificar Estilo                            | `EspecificarEstiloUI` / `…Controller`         | Carolina    |
| UC3  | Especificar Cor                               | `EspecificarCorUI` / `…Controller`            | Carolina    |
| UC4  | Especificar Material                          | `EspecificarMaterialUI` / `…Controller`       | Carolina    |
| UC5  | Especificar Categoria de Peça                 | `EspecificarCategoriaPecaUI` / `…Controller`  | Margarida   |
| UC6  | Especificar Ocasião                           | `EspecificarOcasiaoUI` / `…Controller`        | Margarida   |
| UC7  | Adicionar Peça de Vestuário                   | `AdicionarPecaUI` / `…Controller`             | Margarida   |
| UC8  | Listar Guarda-Roupa                           | `ListarGuardaRoupaUI` / `…Controller`         | Margarida   |
| UC9  | Editar Peça de Vestuário                      | `EditarPecaUI` / `…Controller`                | Matilde     |
| UC10 | Remover Peça de Vestuário                     | `RemoverPecaUI` / `…Controller`               | Matilde     |
| UC11 | Pedir Sugestão de Combinação para uma Ocasião | `PedirSugestaoUI` / `…Controller`             | Matilde     |
| UC12 | Avaliar Sugestão                              | `AvaliarSugestaoUI` / `…Controller`           | Matilde     |
| UC13 | Criar Combinação Manualmente                  | `CriarCombinacaoUI` / `…Controller`           | Sérgio      |
| UC14 | Aceitar/Rejeitar Sugestão                     | `AceitarRejeitarSugestaoUI` / `…Controller`   | Sérgio      |
| UC15 | Consultar Histórico de Sugestões e Avaliações | `ConsultarHistoricoUI` / `…Controller`        | Sérgio      |
| UC16 | Especificar Parte do Corpo                    | `EspecificarParteCorpoUI` / `…Controller`     | Carolina    |
| UC17 | Especificar Tamanho                           | `EspecificarTamanhoUI` / `…Controller`        | Sérgio      |
| UC18 | Especificar Estado da Peça                    | `EspecificarEstadoPecaUI` / `…Controller`     | Sérgio      |

## Equipa

- Margarida Soares – 1241069
- Carolina Costa – 1241084
- Matilde Gomes – 1241301
- Sérgio Fernandes – 1230491
