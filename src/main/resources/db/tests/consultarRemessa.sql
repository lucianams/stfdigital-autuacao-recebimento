/** Protocolo 9009 **/
INSERT INTO RECEBIMENTO.REMESSA(SEQ_PROTOCOLO, SIG_CLASSE, QTD_VOLUME, QTD_APENSO, TIP_FORMA_RECEBIMENTO, NUM_SEDEX, TIP_STATUS, NUM_REMESSA, NUM_ANO, SIG_RECEBEDOR, DAT_RECEBIMENTO, TIP_PROCESSO, TIP_SIGILO) VALUES
(9009, NULL, 1, 1, 'SEDEX', 'SR123456789BR', 'ASSINATURA', 9009, 2016, 'USUARIO_FALSO', DATE '2016-05-31', 'ORIGINARIO', 'PUBLICO');           

INSERT INTO RECEBIMENTO.DEVOLUCAO(SEQ_PROTOCOLO, DSC_MOTIVACAO, SEQ_MOTIVO_DEVOLUCAO, SEQ_MODELO_DOCUMENTO, SEQ_TEXTO) VALUES
(9009, STRINGDECODE('Remessa inv\u00e1lida.'), 1, 1, 9000);        

/** Protocolo 9010 **/
INSERT INTO RECEBIMENTO.REMESSA(SEQ_PROTOCOLO, SIG_CLASSE, QTD_VOLUME, QTD_APENSO, TIP_FORMA_RECEBIMENTO, NUM_SEDEX, TIP_STATUS, NUM_REMESSA, NUM_ANO, SIG_RECEBEDOR, DAT_RECEBIMENTO, TIP_PROCESSO, TIP_SIGILO) VALUES
(9010, NULL, 1, 2, 'BALCAO', NULL, 'PREAUTUACAO', 9010, 2016, 'USUARIO_FALSO', DATE '2016-05-31', 'ORIGINARIO', 'PUBLICO'); 