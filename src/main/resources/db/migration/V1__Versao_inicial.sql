create sequence recebimento.seq_evento increment by 1 start with 1 nomaxvalue minvalue 1 nocycle nocache;

create table recebimento.remessa (seq_protocolo number not null, sig_classe varchar2(6), qtd_volume integer not null, qtd_apenso integer not null, tip_forma_recebimento varchar2(10) not null, num_sedex varchar2(13), tip_status varchar2(15) not null, dsc_motivo varchar2(100), constraint pk_remessa primary key (seq_protocolo));
alter table recebimento.remessa add constraint ck_reme_tip_status check (tip_status in ('PREAUTUACAO', 'RECEBIDA', 'DEVOLUCAO', 'ASSINATURA', 'REJEITADA'));
alter table recebimento.remessa add constraint ck_reme_tip_forma_recebimento check (tip_forma_recebimento in ('BALCAO', 'FAX', 'E_MAIL', 'MALOTE', 'SEDEX'));

create table recebimento.evento (seq_evento number not null, nom_evento varchar2(100) not null, dat_criacao date not null, bin_detalhe clob not null, tip_status smallint not null, constraint pk_evento primary key (seq_evento));

create table recebimento.remessa_evento (seq_protocolo number not null, seq_evento number not null, constraint pk_remessa_evento primary key (seq_protocolo, seq_evento));
alter table recebimento.remessa_evento add constraint fk_remessa_reev foreign key (seq_protocolo) references recebimento.remessa(seq_protocolo);
alter table recebimento.remessa_evento add constraint fk_evento_reev foreign key (seq_evento) references recebimento.evento(seq_evento);