create schema recebimento;

create table recebimento.remessa (seq_protocolo number not null, sig_classe varchar2(6), qtd_volume smallint not null, qtd_apenso smallint not null, tip_forma_recebimento varchar2(10) not null, num_sedex varchar2(13), tip_status varchar2(15) not null, dsc_motivo varchar2(100), tip_processo varchar2(10) not null, constraint pk_remessa primary key (seq_protocolo));
alter table recebimento.remessa add constraint ck_reme_tip_status check (tip_status in ('PREAUTUACAO', 'RECEBIDA', 'DEVOLUCAO', 'ASSINATURA', 'REJEITADA'));
alter table recebimento.remessa add constraint ck_reme_tip_forma_recebimento check (tip_forma_recebimento in ('BALCAO', 'SEDEX', 'MALOTE', 'FAX', 'EMAIL'));
--alter table recebimento.remessa add constraint ck_reme_tip_processo check (tip_processo in ('ORIGINARIO', 'RECURSAL'));