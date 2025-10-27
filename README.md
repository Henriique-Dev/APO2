# ☀️ APO2 - Aplicativo de Previsão do Tempo

Este projeto é um aplicativo móvel de previsão do tempo desenvolvido em **Java** para a plataforma Android, utilizando o Android Studio. O objetivo principal foi demonstrar a integração de múltiplos componentes modernos do Android, como navegação por abas, consumo de API RESTful, exibição de listas dinâmicas e integração de mapas e scanner de QR Code.

## ✨ Funcionalidades Principais

| Funcionalidade | Componentes Utilizados | Requisito Atendido |
| :--- | :--- | :--- |
| **Navegação Inicial (Splash)** | `Activity` e `Handler` | Exibição de logo por 3 segundos. |
| **Previsão em Lista** | `RecyclerView`, `CardView`, `Fragment` | Exibição da previsão de 7 dias da API HG Brasil. |
| **Consumo de API** | Retrofit 2 e GSON | Conexão com o endpoint de clima da HG Brasil. |
| **Mapa Interativo** | Google Maps SDK, `Fragment` | Exibição de um marcador fixo na cidade de consulta (Cidade Gaúcha, PR). |
| **Troca de Cidade via QR Code** | `FloatingActionButton`, Zxing Library | Inicia o scanner para ler um WOEID/CID e atualizar a previsão e o mapa. |
| **Navegação Principal** | `TabLayout` e `ViewPager2` | Navegação por abas entre "Previsão" e "Mapa". |
| **Menu e Informações** | `Toolbar` customizada, `Menu` | Acesso à tela "Sobre" com informações pessoais do acadêmico. |
| **Padrão Visual** | Material Design | Uso de componentes e cores seguindo o padrão Google. |

## 🛠️ Tecnologias e Dependências

O projeto utiliza as seguintes bibliotecas e tecnologias:

*   **Linguagem:** Java
*   **Ambiente:** Android Studio
*   **API REST:** [Retrofit 2](https://square.github.io/retrofit/ ) (para requisições HTTP)
*   **JSON Parsing:** GSON (para conversão de JSON em objetos Java - POJOs)
*   **Listas:** `RecyclerView` e `CardView`
*   **Navegação:** `ViewPager2` e `TabLayout`
*   **Mapa:** Google Maps SDK (`play-services-maps`)
*   **QR Code:** [Zxing Android Embedded](https://github.com/journeyapps/zxing-android-embedded )

### Dependências Adicionadas (`build.gradle (app)`)

```groovy
// Navegação
implementation 'androidx.viewpager2:viewpager2:1.0.0'
implementation 'com.google.android.material:material:1.12.0' // Versão mais recente

// Listas
implementation 'androidx.recyclerview:recyclerview:1.3.2'
implementation 'androidx.cardview:cardview:1.0.0'

// API REST
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

// Mapa
implementation 'com.google.android.gms:play-services-maps:18.2.0'

// QR Code
implementation 'com.journeyapps:zxing-android-embedded:4.3.0'
