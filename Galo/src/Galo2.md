# Permitir jogar em duas instâncias diferentes

## Passam a existir os comandos:
* **Start** para o primeiro jogador (X) iniciar uma partida 
com um determinado nome;
* **Join** para o segundo jogador (O) se juntar a uma partida, 
indicando também o nome;
* **Refresh** para consultar o estado da partida quando um jogador
espera que o outro jogue.

O comando **New** só é permitido ao jogador que tem a vez,
se o jogo ainda não terminou, ou ao jogador inicial do 
próximo jogo.

Para não ficarem armazenadas partidas já terminadas, 
estas devem ser removidas, quando for abandonada 
pelo jogador que a iniciou (que faz Start). 
Este abandono é detectado no Exit, no Start ou no Join.
Vamos convencionar que o nome de uma partida é uma só palavra, com pelo menos um símbolo, que só pode conter letras
