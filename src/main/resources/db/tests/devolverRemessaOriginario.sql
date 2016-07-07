INSERT INTO RECEBIMENTO.REMESSA(SEQ_PROTOCOLO, SIG_CLASSE, QTD_VOLUME, QTD_APENSO, TIP_FORMA_RECEBIMENTO, NUM_SEDEX, TIP_STATUS, NUM_REMESSA, NUM_ANO, SIG_RECEBEDOR, DAT_RECEBIMENTO, TIP_PROCESSO, TIP_SIGILO) VALUES
(9001, NULL, 1, 1, 'SEDEX', 'SR123456789BR', 'PREAUTUACAO', 9001, 2016, 'USUARIO_FALSO', DATE '2016-05-31', 'ORIGINARIO', 'PUBLICO');              

INSERT INTO PUBLIC.ACT_GE_BYTEARRAY(ID_, REV_, NAME_, DEPLOYMENT_ID_, BYTES_, GENERATED_) VALUES
('-27', 1, 'var-informationId', NULL, X'aced00057372002c62722e6a75732e7374662e636f72652e7368617265642e70726f746f636f6c6f2e50726f746f636f6c6f496400000000000000010200014c000269647400104c6a6176612f6c616e672f4c6f6e673b78707372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000000000000d', NULL),
('-29', 1, 'hist.var-informationId', NULL, X'aced00057372002c62722e6a75732e7374662e636f72652e7368617265642e70726f746f636f6c6f2e50726f746f636f6c6f496400000000000000010200014c000269647400104c6a6176612f6c616e672f4c6f6e673b78707372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000000000000d', NULL); 

INSERT INTO PUBLIC.ACT_RU_EXECUTION(ID_, REV_, PROC_INST_ID_, BUSINESS_KEY_, PARENT_ID_, PROC_DEF_ID_, SUPER_EXEC_, ACT_ID_, IS_ACTIVE_, IS_CONCURRENT_, IS_SCOPE_, IS_EVENT_SCOPE_, SUSPENSION_STATE_, CACHED_ENT_STATE_, TENANT_ID_, NAME_, LOCK_TIME_) VALUES
('-24', 1, '-24', 'RE:9001', NULL, 'recebimento:1:3', NULL, 'preautuar-originario', TRUE, FALSE, TRUE, FALSE, 1, 2, NULL, NULL, NULL);              

INSERT INTO PUBLIC.ACT_RU_TASK(ID_, REV_, EXECUTION_ID_, PROC_INST_ID_, PROC_DEF_ID_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, TASK_DEF_KEY_, OWNER_, ASSIGNEE_, DELEGATION_, PRIORITY_, CREATE_TIME_, DUE_DATE_, CATEGORY_, SUSPENSION_STATE_, TENANT_ID_, FORM_KEY_) VALUES
('-32', 1, '-24', '-24', 'recebimento:1:3', STRINGDECODE('Pr\u00e9-Autuar Remessa de Processo Origin\u00e1rio'), NULL, 'PREAUTUACAO', 'preautuar-originario', NULL, NULL, NULL, 50, TIMESTAMP '2016-05-31 17:42:37.772', NULL, NULL, 1, NULL, NULL);       

INSERT INTO PUBLIC.ACT_RU_IDENTITYLINK(ID_, REV_, GROUP_ID_, TYPE_, USER_ID_, TASK_ID_, PROC_INST_ID_, PROC_DEF_ID_) VALUES
('-33', 1, 'recebedor-originarios', 'candidate', NULL, '-32', NULL, NULL);     

INSERT INTO PUBLIC.ACT_RU_VARIABLE(ID_, REV_, TYPE_, NAME_, EXECUTION_ID_, PROC_INST_ID_, TASK_ID_, BYTEARRAY_ID_, DOUBLE_, LONG_, TEXT_, TEXT2_) VALUES
('-26', 1, 'string', 'transition', '-24', '-24', NULL, NULL, NULL, NULL, 'ORIGINARIO', NULL),
('-28', 1, 'serializable', 'informationId', '-24', '-24', NULL, '-27', NULL, NULL, NULL, NULL);  

INSERT INTO PUBLIC.ACT_HI_PROCINST(ID_, PROC_INST_ID_, BUSINESS_KEY_, PROC_DEF_ID_, START_TIME_, END_TIME_, DURATION_, START_USER_ID_, START_ACT_ID_, END_ACT_ID_, SUPER_PROCESS_INSTANCE_ID_, DELETE_REASON_, TENANT_ID_, NAME_) VALUES
('-24', '-24', 'RE:9001', 'recebimento:1:3', TIMESTAMP '2016-05-31 17:42:37.771', NULL, NULL, NULL, 'inicio', NULL, NULL, NULL, NULL, NULL);          

INSERT INTO PUBLIC.ACT_HI_ACTINST(ID_, PROC_DEF_ID_, PROC_INST_ID_, EXECUTION_ID_, ACT_ID_, TASK_ID_, CALL_PROC_INST_ID_, ACT_NAME_, ACT_TYPE_, ASSIGNEE_, START_TIME_, END_TIME_, DURATION_, TENANT_ID_) VALUES
('-25', 'recebimento:1:3', '-24', '-24', 'inicio', NULL, NULL, NULL, 'startEvent', NULL, TIMESTAMP '2016-05-31 17:42:37.771', TIMESTAMP '2016-05-31 17:42:37.772', 1, NULL),
('-30', 'recebimento:1:3', '-24', '-24', 'preautuacao-transicoes', NULL, NULL, NULL, 'exclusiveGateway', NULL, TIMESTAMP '2016-05-31 17:42:37.772', TIMESTAMP '2016-05-31 17:42:37.772', 0, NULL),
('-31', 'recebimento:1:3', '-24', '-24', 'preautuar-originario', '-32', NULL, STRINGDECODE('Pr\u00e9-Autuar Remessa de Processo Origin\u00e1rio'), 'userTask', NULL, TIMESTAMP '2016-05-31 17:42:37.772', NULL, NULL, NULL);            

INSERT INTO PUBLIC.ACT_HI_TASKINST(ID_, PROC_DEF_ID_, TASK_DEF_KEY_, PROC_INST_ID_, EXECUTION_ID_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, OWNER_, ASSIGNEE_, START_TIME_, CLAIM_TIME_, END_TIME_, DURATION_, DELETE_REASON_, PRIORITY_, DUE_DATE_, FORM_KEY_, CATEGORY_, TENANT_ID_) VALUES
('-32', 'recebimento:1:3', 'preautuar-originario', '-24', '-24', STRINGDECODE('Pr\u00e9-Autuar Remessa de Processo Origin\u00e1rio'), NULL, 'PREAUTUACAO', NULL, NULL, TIMESTAMP '2016-05-31 17:42:37.772', NULL, NULL, NULL, NULL, 50, NULL, NULL, NULL, NULL);   

INSERT INTO PUBLIC.ACT_HI_VARINST(ID_, PROC_INST_ID_, EXECUTION_ID_, TASK_ID_, NAME_, VAR_TYPE_, REV_, BYTEARRAY_ID_, DOUBLE_, LONG_, TEXT_, TEXT2_, CREATE_TIME_, LAST_UPDATED_TIME_) VALUES
('-26', '-24', '-24', NULL, 'transition', 'string', 0, NULL, NULL, NULL, 'ORIGINARIO', NULL, TIMESTAMP '2016-05-31 17:42:37.771', TIMESTAMP '2016-05-31 17:42:37.771'),
('-28', '-24', '-24', NULL, 'informationId', 'serializable', 0, '-29', NULL, NULL, NULL, NULL, TIMESTAMP '2016-05-31 17:42:37.771', TIMESTAMP '2016-05-31 17:42:37.771'); 

INSERT INTO PUBLIC.ACT_HI_IDENTITYLINK(ID_, GROUP_ID_, TYPE_, USER_ID_, TASK_ID_, PROC_INST_ID_) VALUES
('-33', 'recebedor-originarios', 'candidate', NULL, '-32', NULL);    