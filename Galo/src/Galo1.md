### Esta  versão da aplicação deve aceitar os comandos:
 - **NEW** - Cria um novo jogo, em que o primeiro a jogar alterna relativamente ao jogo anterior.
 - **PLAY** <pos> - Faz uma jogada (do jogador que tem a vez) na posição indicada (0..8)
 - **EXIT** - Termina a aplicação

No final da execução de cada comando é apresentado o tabuleiro e o estado atual, por exemplo, de quem é a vez, ou se o jogo terminou e quem venceu, ou se foi um empate.

### Uma possível interação desta aplicação em que o texto de input é apresentado a bold, será:
<pre><font size="4">
> new
   |   |
---+---+---
   |   |
---+---+---
   |   |
turn: X
> play 4
   |   |
---+---+---
   | X |
---+---+---
   |   |
turn: O
> play 3
   |   |
---+---+---
 O | X |
---+---+---
   |   |
turn: X
> play 1
   | X |
---+---+---
 O | X |
---+---+---
   |   |
turn: O
> play 6
   | X |
---+---+---
 O | X |
---+---+---
 O |   |
turn: X
> play 7
   | X |
---+---+---
 O | X |
---+---+---
 O | X |
winner: X
> new
   |   |
---+---+---
   |   |
---+---+---
   |   |
turn: O
> play 4
   |   |
---+---+---
   | O |
---+---+---
   |   |
turn: X
> exit
</font></pre>

