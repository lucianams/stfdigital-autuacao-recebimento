-- SELECT sig_classe, dsc_classe nom_classe, DECODE(tip_categoria_classe, 'PO', 'ORIGINARIO', 'RECURSAL') tip_processo FROM judiciario.classe WHERE flg_ativo = 'S' AND flg_admite_meio_eletronico = 'S';
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('RE','RECURSO EXTRAORDINÁRIO','RECURSAL');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('ADI','AÇÃO DIRETA DE INCONSTITUCIONALIDADE','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('ACO','AÇÃO CÍVEL ORIGINÁRIA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('HD','HABEAS DATA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('MS','MANDADO DE SEGURANÇA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('AO','AÇÃO ORIGINÁRIA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('IF','INTERVENÇÃO FEDERAL','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('AR','AÇÃO RESCISÓRIA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('EP','EXECUÇÃO PENAL','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('SS','SUSPENSÃO DE SEGURANÇA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('CC','CONFLITO DE COMPETÊNCIA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('OACO','OPOSIÇÃO EM AÇÃO CIVIL ORIGINÁRIA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('ADC','AÇÃO DECLARATÓRIA DE CONSTITUCIONALIDADE','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('AOE','AÇÃO ORIGINÁRIA ESPECIAL','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('AS','ARGÜIÇÃO DE SUSPEIÇÃO','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('ADPF','ARGÜIÇÃO DE DESCUMPRIMENTO DE PRECEITO FUNDAMENTAL','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('SL','SUSPENSÃO DE LIMINAR','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('Cm','COMUNICAÇÃO','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('Pet','PETIÇÃO','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('STA','SUSPENSÃO DE TUTELA ANTECIPADA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('ADO','AÇÃO DIRETA DE INCONSTITUCIONALIDADE POR OMISSÃO','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('PSV','PROPOSTA DE SÚMULA VINCULANTE','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('AImp','ARGÜIÇÃO DE IMPEDIMENTO','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('EL','EXCEÇÃO DE LITISPENDÊNCIA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('EI','EXCEÇÃO DE INCOMPETÊNCIA','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('RHC','RECURSO ORDINÁRIO EM HABEAS CORPUS','RECURSAL');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('HC','HABEAS CORPUS','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('Rcl','RECLAMAÇÃO','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('ARE','RECURSO EXTRAORDINÁRIO COM AGRAVO','RECURSAL');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('AI','AGRAVO DE INSTRUMENTO','RECURSAL');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('MI','MANDADO DE INJUNÇÃO','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('AC','AÇÃO CAUTELAR','ORIGINARIO');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('RMS','RECURSO ORD. EM MANDADO DE SEGURANÇA','RECURSAL');
Insert into RECEBIMENTO.CLASSE_PETICIONAVEL (SIG_CLASSE,NOM_CLASSE,TIP_PROCESSO) values ('RvC','REVISÃO CRIMINAL','ORIGINARIO');

-- SELECT seq_tipo_preferencia seq_preferencia, dsc_preferencia nom_preferencia FROM judiciario.tipo_preferencia WHERE seq_tipo_preferencia IN (SELECT seq_tipo_preferencia FROM judiciario.classe_tipo_preferencia WHERE flg_ativo = 'S' AND sig_classe IN (SELECT sig_classe FROM judiciario.classe WHERE flg_ativo = 'S' AND flg_admite_meio_eletronico = 'S'));
Insert into RECEBIMENTO.PREFERENCIA (SEQ_PREFERENCIA,NOM_PREFERENCIA) values (3,'Eleitoral');
Insert into RECEBIMENTO.PREFERENCIA (SEQ_PREFERENCIA,NOM_PREFERENCIA) values (8,'Medida Liminar');
Insert into RECEBIMENTO.PREFERENCIA (SEQ_PREFERENCIA,NOM_PREFERENCIA) values (2,'Criminal');
Insert into RECEBIMENTO.PREFERENCIA (SEQ_PREFERENCIA,NOM_PREFERENCIA) values (12,'Réu Preso');
Insert into RECEBIMENTO.PREFERENCIA (SEQ_PREFERENCIA,NOM_PREFERENCIA) values (1,'Maior de 60 anos ou portador de doença grave');
Insert into RECEBIMENTO.PREFERENCIA (SEQ_PREFERENCIA,NOM_PREFERENCIA) values (15,'Assistência Judiciária Gratuita');

-- SELECT sig_classe, seq_tipo_preferencia seq_preferencia FROM judiciario.classe_tipo_preferencia WHERE flg_ativo = 'S' AND sig_classe IN (SELECT sig_classe FROM judiciario.classe WHERE flg_ativo = 'S' AND flg_admite_meio_eletronico = 'S');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RE','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RE','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RE','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RE','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RE','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RE','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ADI','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ADI','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ACO','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ACO','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ACO','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ACO','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ACO','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ACO','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('HD','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('HD','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('HD','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('MS','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('MS','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('MS','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('MS','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('MS','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('MS','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AO','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AO','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AO','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AO','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AO','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AO','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('IF','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('IF','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AR','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AR','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AR','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AR','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EP','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EP','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EP','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SS','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SS','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SS','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SS','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SS','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('CC','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('CC','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ADC','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ADC','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AOE','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AOE','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AOE','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AS','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AS','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AS','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AS','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ADPF','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ADPF','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SL','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SL','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SL','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SL','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SL','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('SL','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Cm','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Cm','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Cm','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Pet','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Pet','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Pet','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Pet','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Pet','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Pet','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('STA','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('STA','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('STA','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('STA','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('STA','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ADO','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ADO','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('PSV','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AImp','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AImp','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AImp','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AImp','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EL','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EL','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EL','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EL','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EI','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EI','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EI','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('EI','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RHC','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RHC','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RHC','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RHC','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RHC','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RHC','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('HC','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('HC','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('HC','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('HC','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Rcl','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Rcl','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Rcl','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Rcl','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Rcl','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('Rcl','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ARE','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ARE','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ARE','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ARE','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ARE','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('ARE','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AI','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AI','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AI','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AI','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AI','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AI','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('MI','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('MI','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AC','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AC','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AC','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AC','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AC','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('AC','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RMS','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RMS','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RMS','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RMS','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RMS','15');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RvC','2');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RvC','3');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RvC','12');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RvC','1');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RvC','8');
Insert into RECEBIMENTO.CLASSE_PREFERENCIA (SIG_CLASSE,SEQ_PREFERENCIA) values ('RvC','15');

-- SELECT seq_motivo_devolucao, dsc_motivo_devolucao FROM stf.motivo_devolucao;
Insert into RECEBIMENTO.MOTIVO_DEVOLUCAO (SEQ_MOTIVO_DEVOLUCAO,DSC_MOTIVO_DEVOLUCAO) values (recebimento.seq_motivo_devolucao.nextval,'Faltam Peças');
Insert into RECEBIMENTO.MOTIVO_TIPO_DOCUMENTO (SEQ_MOTIVO_DEVOLUCAO, SEQ_TIPO_DOCUMENTO) values (recebimento.seq_motivo_devolucao.currval, 8);
Insert into RECEBIMENTO.MOTIVO_DEVOLUCAO (SEQ_MOTIVO_DEVOLUCAO,DSC_MOTIVO_DEVOLUCAO) values (recebimento.seq_motivo_devolucao.nextval,'AI intempestivo');
Insert into RECEBIMENTO.MOTIVO_TIPO_DOCUMENTO (SEQ_MOTIVO_DEVOLUCAO, SEQ_TIPO_DOCUMENTO) values (recebimento.seq_motivo_devolucao.currval, 8);
Insert into RECEBIMENTO.MOTIVO_DEVOLUCAO (SEQ_MOTIVO_DEVOLUCAO,DSC_MOTIVO_DEVOLUCAO) values (recebimento.seq_motivo_devolucao.nextval,'RE intempestivo');
Insert into RECEBIMENTO.MOTIVO_TIPO_DOCUMENTO (SEQ_MOTIVO_DEVOLUCAO, SEQ_TIPO_DOCUMENTO) values (recebimento.seq_motivo_devolucao.currval, 8);

Insert into RECEBIMENTO.MODELO_DEVOLUCAO (SEQ_MODELO_DOCUMENTO,NOM_MODELO_DOCUMENTO,SEQ_TIPO_DOCUMENTO,SEQ_DOCUMENTO_TEMPLATE) values (1,'Ofício de devolução de remessa',8,2);

/*
Insert into RECEBIMENTO.REMESSA(seq_protocolo, sig_classe, qtd_volume, qtd_apenso, tip_forma_recebimento, num_sedex, tip_status, num_remessa, num_ano, sig_recebedor, dat_recebimento, tip_processo, tip_sigilo) values (1, 'RE' , 1, 1, 'SEDEX', '123456789BR', 'RECEBIDA', 1, 2016, 'KAKAROTO', SYSDATE, 'RECURSAL', 'PUBLICO');
Insert into RECEBIMENTO.REMESSA(seq_protocolo, sig_classe, qtd_volume, qtd_apenso, tip_forma_recebimento, num_sedex, tip_status, num_remessa, num_ano, sig_recebedor, dat_recebimento, tip_processo, tip_sigilo) values (2, 'ADI', 2, 1, 'SEDEX', '444555667BR', 'RECEBIDA', 2, 2016, 'JASPION', SYSDATE, 'RECURSAL', 'PUBLICO');
Insert into RECEBIMENTO.REMESSA(seq_protocolo, sig_classe, qtd_volume, qtd_apenso, tip_forma_recebimento, num_sedex, tip_status, num_remessa, num_ano, sig_recebedor, dat_recebimento, tip_processo, tip_sigilo) values (3, 'HD' , 3, 2, 'MALOTE', '', 'RECEBIDA', 3, 2016, 'SHADOW MOON', SYSDATE, 'ORIGINARIO', 'PUBLICO');
Insert into RECEBIMENTO.REMESSA(seq_protocolo, sig_classe, qtd_volume, qtd_apenso, tip_forma_recebimento, num_sedex, tip_status, num_remessa, num_ano, sig_recebedor, dat_recebimento, tip_processo, tip_sigilo) values (4, 'HD' , 4, 1, 'BALCAO', '', 'RECEBIDA', 4, 2016, 'VINICIUS', SYSDATE, 'ORIGINARIO', 'PUBLICO');
*/

