# 🎮 Sistema de Combate de Tanques - JavaFX

## ✨ Funcionalidades Implementadas

### 🛠️ Criação de Tanques
- **Interface Visual Completa**: Sliders para ajustar atributos (Blindagem, Velocidade, Poder de Fogo)
- **Sistema de Pontos Balanceado**: Total de 300 pontos para distribuir entre atributos
- **Tipos de Tanque**: Leve, Médio, Pesado com diferentes bônus
- **Preview em Tempo Real**: Visualização do tanque com ASCII art
- **Gerador Aleatório**: Crie tanques com stats aleatórios
- **Validação**: Sistema impede criação de tanques desequilibrados

### ⚔️ Sistema de Combate Completo
- **Batalhas em Turnos**: Sistema de combate baseado em turnos
- **4 Ações de Combate**:
  - 💥 **ATACAR**: Causa dano baseado no Poder de Fogo vs Blindagem
  - 🛡️ **DEFENDER**: Reduz dano recebido pela metade no próximo turno
  - ⚡ **ESPECIAL**: Ataque com 50% mais dano
  - 🏳️ **DESISTIR**: Sair da batalha com confirmação

### 🤖 IA do Inimigo
- **Comportamento Inteligente**: 70% chance de atacar, 30% de defender
- **Geração Automática**: Cria inimigos aleatórios se não houver
- **Nomes Únicos**: Pool de nomes intimidadores para inimigos
- **Stats Balanceados**: Inimigos têm force equivalente ao jogador

### 📊 Sistema de Estatísticas
- **Contadores de Batalha**: Vitórias, derrotas e empates
- **Barras de Vida**: Representação visual da saúde em tempo real
- **Log de Combate**: Histórico detalhado de todas as ações
- **Rounds**: Sistema de contagem de rodadas

### 🎨 Interface Moderna
- **Design Responsivo**: Interface adaptável e visualmente atraente
- **Cores Dinâmicas**: Barras de vida mudam de cor conforme a saúde
- **Animações**: Transições suaves entre telas e ações
- **ASCII Art**: Representação visual diferente para cada tipo de tanque
- **Toast Notifications**: Feedback visual para ações do usuário

### 🎯 Mecânicas de Jogo
- **Sistema de Dano**: Cálculo baseado em Poder de Fogo vs Blindagem
- **Defesa Ativa**: Defender reduz dano do próximo ataque inimigo
- **Randomização**: Elemento aleatório no dano para variabilidade
- **Classes de Tanque**:
  - ⚡ **Leve**: +20% Velocidade, -10% Blindagem
  - ⚔️ **Médio**: Balanceado, +10% Poder de Fogo  
  - 🛡️ **Pesado**: +30% Blindagem, -20% Velocidade

### 🎮 Controles e Navegação
- **Controles Intuitivos**: Botões grandes e claramente identificados
- **Confirmações**: Diálogos de confirmação para ações críticas
- **Voltar Seguro**: Aviso ao tentar sair durante batalha ativa
- **Jogar Novamente**: Opção de reiniciar batalha rapidamente

## 🚀 Como Executar

1. **Navegue até o diretório do projeto**:
   ```bash
   cd "C:\Users\T-Gamer\Downloads\COD JAVA GITHUB\gradle"
   ```

2. **Execute o aplicativo**:
   ```bash
   .\gradlew --no-daemon --no-configuration-cache run
   ```

3. **Navegue pela interface**:
   - **Menu Principal**: Escolha "Criar Tanques" ou "Arena de Combate"
   - **Criação**: Configure seu tanque e teste em combate
   - **Combate**: Use as ações estrategicamente para vencer!

## 🎯 Estratégias de Combate

### 💡 Dicas para Vencer
- **Use DEFENDER** quando sua vida estiver baixa
- **ESPECIAL** é mais eficaz contra inimigos com pouca blindagem
- **Tanques Pesados** são ótimos para defesa prolongada
- **Tanques Leves** podem usar velocidade para ataques rápidos
- Observe o padrão de comportamento do inimigo

### ⚖️ Balanceamento
- **Blindagem**: Reduz dano recebido (1 ponto de armor = -1 dano a cada 5 pontos)
- **Poder de Fogo**: Aumenta dano causado (1 ponto = +1 dano a cada 3 pontos)
- **Velocidade**: Futuramente pode afetar ordem de turnos

## 🛠️ Tecnologias Utilizadas
- **JavaFX 21**: Interface gráfica moderna
- **FXML**: Estruturação declarativa da UI
- **CSS**: Estilização avançada e responsiva
- **Gradle**: Build system e gerenciamento de dependências
- **Java 20**: Linguagem de programação base

## 🎨 Características Visuais
- **Tema Dark**: Interface escura moderna
- **Cores Contrastantes**: Boa visibilidade em todos os elementos
- **Gradients**: Efeitos visuais sutis sem comprometer performance
- **Icons Emoji**: Interface amigável e intuitiva
- **Feedback Visual**: Animações e transições suaves

---

**Status**: ✅ **FUNCIONAL E COMPLETO**
**Última Atualização**: 29/09/2024
**Versão**: 2.0 - Sistema de Combate Implementado