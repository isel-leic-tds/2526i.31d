## Esta primeira abordagem tem vários problemas:

### 1. Não faz uma separação adequada entre o modelo (regras do jogo) e a interação com o utilizador.
   O código de Game.kt está comprometido com a representação textual das jogadas (X e O).

   A função main tem código com lógica do jogo, nomeadamente o ramo do when responsável pelo comando PLAY.  

   A função Game.show não deveria estar em  Game.kt porque é de apresentação. Um futuro programa com interface gráfica não necessita desta função.

### 2. Não reporta adequadamente os erros cometidos pelo utilizador.
   Se o utilizador indicar uma posição inválida, por exemplo 9, ou se não indicar qualquer posição é reportada uma exceção, que faz abortar o programa.

   Se for indicada uma posição já ocupada a jogada não é realizada, mas o utilizador não é notificado.
### 3. Não é extensível porque não é fácil de escalar para novos requisitos.
   Se for necessário acrescentar mais comandos é necessário alterar a função main e esta poderá ficar com uma dimensão exagerada, ou seja, a solução não é escalável para mais comandos.
   
   Deveria ser possível alterar a dimensão do tabuleiro modificando apenas o valor de uma constante nomeada, por exemplo: BOARD_SIZE=3.
### 4. Permite representar estados inválidos.
   Não deveria ser possível, por exemplo, criar um jogo com Game('?','A',emptyList()).

   É usado o tipo Char para representar o jogador, admitindo que só terá os valores  'X' e 'O'.
### 5. Verifica as condições de vitória cada vez que o tabuleiro é apresentado.
   As condições de vitória devem ser verificadas em cada jogada e só para a posição jogada, e não por cada apresentação do tabuleiro e para todas as possibilidades.
### 6. Permite jogar num jogo onde já existe vencedor.
