# API de Pagamentos

SonarCloud QUALITY GATE STATUS: PASSED - View details on https://sonarcloud.io/dashboard?id=Eduhkbr_ToolsChallenge&branch=main

As operações serão as seguintes:
- Pagamento:
- Solicitação e resposta conforme JSON abaixo
- Estorno:
- consulta: por ID
- retorno: conforme JSON de retorno de estorno
- Consulta:
- consulta: todos e por ID
- retorno: conforme JSON de retorno do pagamento
  A API deve utilizar conceitos REST, JSON e protocolo padrão HTTP de retorno.
  O servidor da aplicação pode ser Tomcat, JBoss ou pode ser utilizado SpringBoot.
  Requisitos dastransações:
- Transacao > id: Deve ser único
- Transacao > Status: “AUTORIZADO”, “NEGADO”
- formaPagamento > Tipo: “AVISTA”, “PARCELADO LOJA”, “PARCELADO EMISSOR”


OBS.:- Transacao > Status: “AUTORIZADO”, “NEGADO" -> 
No exemplo de resposta de estorno tem uma terceira opção, "CANCELADO".

- Pagamento:
  - Solicitação e resposta conforme JSON abaixo
  - POST: http://localhost:8080/pagamento 
  - JSON Request:
  - {
    "transacao": {
    "cartao": "4444********1234",
    "id": 100023568900001,
    "descricao": {
    "valor": 500.50,
    "dataHora": "01/05/2021 18:30:00",
    "estabelecimento": "PetShop Mundo cão"
    },
    "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": 1
    }
    }
    }
  - JSON Response:
  - {
    "transacao": {
    "id": 100023568900001,
    "cartao": "4444********1234",
    "descricao": {
    "valor": 500.50,
    "dataHora": "01/05/2021 18:30:00",
    "estabelecimento": "PetShop Mundo cão",
    "nsu": "1234567890",
    "codigoAutorizacao": "147258369",
    "status": "AUTORIZADO"
    },
    "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": 1
    }
    }
    }

- Estorno:
  - consulta: por ID
  - retorno: conforme JSON de retorno de estorno
  - GET: http://localhost:8080/pagamento/estorno/100023568900001
  - JSON response:
  - {
    "transacao": {
    "id": 100023568900001,
    "cartao": "4444********1234",
    "descricao": {
    "valor": 500.50,
    "dataHora": "01/05/2021 18:30:00",
    "estabelecimento": "PetShop Mundo cão",
    "nsu": "1234567890",
    "codigoAutorizacao": "147258369",
    "status": "CANCELADO"
    },
    "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": 1
    }
    }
    }
- Consulta:
  - consulta: todos e por ID
  - retorno: conforme JSON de retorno do pagamento
  - GET todos: http://localhost:8080/pagamento/consulta
  - JSON response:
  - [
    {
    "transacao": {
    "id": 100023568900001,
    "cartao": "4444********1234",
    "descricao": {
    "valor": 500.50,
    "dataHora": "01/05/2021 18:30:00",
    "estabelecimento": "PetShop Mundo cão",
    "nsu": "1234567890",
    "codigoAutorizacao": "147258369",
    "status": "AUTORIZADO"
    },
    "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": 1
    }
    }
    }
    ]
  - GET id: http://localhost:8080/pagamento/consulta/100023568900001
  - {
    "transacao": {
    "id": 100023568900001,
    "cartao": "4444********1234",
    "descricao": {
    "valor": 500.50,
    "dataHora": "01/05/2021 18:30:00",
    "estabelecimento": "PetShop Mundo cão",
    "nsu": "1234567890",
    "codigoAutorizacao": "147258369",
    "status": "AUTORIZADO"
    },
    "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": 1
    }
    }
    }
