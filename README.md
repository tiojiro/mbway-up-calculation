# MBWay UP - Cálculo das datas dos Prêmios

### Regra para o Prêmio Diário
"Atribuído todos os dias com início a 22 de Setembro de 2021 às 07:12. Nos dias seguintes é atribuído em horário diferente, com a seguinte lógica: 
HORA do Prêmio Diário do dian anterior +1 (+7 se o resultado for > que 23);
MINUTO do Prêmio Diário do dia anterior +24 (-60 se o resultado for > que 59)."

### Regra para o Prêmio Semanal
"Atribuído todas as semanas com início no dia 25 de Setembro de 2021 às 21:45. Nas semanas seguintes é atribuído em dia da semana diferente, (Terça, Quarta, Quinta, Sexta, Sábado, Domingo e Segunda) consecutivamente, num horário estabelecido do seguinte modo: 
HORA = às Segundas - 11h, às Terças - 13h, às Quartas - 15h, às Quintas - 17h, às Sextas - 19h, aos Sábados - 21h e aos Domingos - 9h; 
MINUTOS = aos 15 min e 45 min alternadamente."

### Regra para o Prêmio Mensal
"Atrbuído todos os últimos Sábados de cada mês, com início no dia 25 de Setembro de 2021 às 13:00. Nos meses seguintes, em cada último Sábado, o horário altera para Hora do Prêmio Mensal do mês anterior +1 (+7 se o resultado for > que 23)."

### Como utilizar
Buscar as próximas 10 datas para o prêmio diário:
<br>http://localhost:8080/calc/daily?numberOfDays=10
<br>
<br>Buscar as próximas 10 datas para o prêmio semanal:
<br>http://localhost:8080/calc/weekly?numberOfWeeks=10
<br>
<br>Buscar as próximas 10 datas para o prêmio mensal:
<br>http://localhost:8080/calc/monthly?numberOfMonths=10