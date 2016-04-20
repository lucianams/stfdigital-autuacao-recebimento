create table recebimento.classe_peticionavel (sig_classe varchar2(6) not null, nom_classe varchar2(100) not null, constraint pk_classe_peticionavel primary key (sig_classe));

create table recebimento.preferencia (seq_preferencia number not null, nom_preferencia varchar2(100) not null, constraint pk_preferencia primary key (seq_preferencia));

create table recebimento.classe_preferencia (sig_classe varchar2(6) not null, seq_preferencia number not null, constraint pk_classe_preferencia primary key (sig_classe, seq_preferencia));
alter table recebimento.classe_preferencia add constraint fk_classe_peticionavel_clpr foreign key (sig_classe) references recebimento.classe_peticionavel(sig_classe);
alter table recebimento.classe_preferencia add constraint fk_preferencia_clpr foreign key (seq_preferencia) references recebimento.preferencia(seq_preferencia);

create table recebimento.remessa_preferencia (seq_protocolo number not null, seq_preferencia number not null, constraint pk_remessa_preferencia primary key (seq_protocolo, seq_preferencia));
alter table recebimento.remessa_preferencia add constraint fk_remessa_repr foreign key (seq_protocolo) references recebimento.remessa(seq_protocolo);
alter table recebimento.remessa_preferencia add constraint fk_preferencia_repr foreign key (seq_preferencia) references recebimento.preferencia(seq_preferencia);

create table recebimento.modelo_devolucao (seq_modelo_documento number not null, nom_modelo_documento varchar2(50) not null, seq_tipo_documento number not null, seq_documento_template number not null, constraint pk_modelo_devolucao primary key (seq_modelo_documento));

create sequence recebimento.seq_motivo_devolucao increment by 1 start with 1 nomaxvalue minvalue 1 nocycle nocache;

create table recebimento.motivo_devolucao (seq_motivo_devolucao number not null, dsc_motivo_devolucao varchar2(50) not null, constraint pk_motivo_devolucao primary key (seq_motivo_devolucao));
alter table recebimento.motivo_devolucao add constraint uk_mode_dsc_motivo_devolucao unique (dsc_motivo_devolucao);

create table recebimento.motivo_tipo_documento (seq_motivo_devolucao number not null, seq_tipo_documento number not null, constraint pk_motivo_tipo_documento primary key (seq_motivo_devolucao, seq_tipo_documento));
alter table recebimento.motivo_tipo_documento add constraint fk_motivo_devolucao_motd foreign key (seq_motivo_devolucao) references recebimento.motivo_devolucao(seq_motivo_devolucao);

create table recebimento.devolucao (seq_protocolo number not null, dsc_motivacao varchar2(1000) not null, seq_motivo_devolucao number, seq_modelo_documento number, seq_texto number, constraint pk_devolucao primary key (seq_protocolo));
alter table recebimento.devolucao add constraint fk_remessa_devo foreign key (seq_protocolo) references recebimento.remessa(seq_protocolo);
alter table recebimento.devolucao add constraint fk_motivo_devolucao_devo foreign key (seq_motivo_devolucao) references recebimento.motivo_devolucao(seq_motivo_devolucao);
alter table recebimento.devolucao add constraint fk_modelo_devolucao_devo foreign key (seq_modelo_documento) references recebimento.modelo_devolucao(seq_modelo_documento);

alter table recebimento.remessa drop column dsc_motivo;
alter table recebimento.remessa add column sig_recebedor varchar2(30) not null;
alter table recebimento.remessa add column dat_recebimento date not null;