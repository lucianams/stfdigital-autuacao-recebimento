alter table recebimento.classe_peticionavel add column tip_processo varchar2(10) not null;
alter table recebimento.classe_peticionavel add constraint ck_clpe_tip_processo check (tip_processo in ('ORIGINARIO', 'RECURSAL'));

alter table recebimento.remessa add column tip_processo varchar2(10) not null;
alter table recebimento.remessa add constraint ck_reme_tip_processo check (tip_processo in ('ORIGINARIO', 'RECURSAL'));
alter table recebimento.remessa add column tip_sigilo varchar2(15) not null;
alter table recebimento.remessa add constraint ck_reme_tip_sigilo check (tip_sigilo in ('PUBLICO', 'SEGREDO_JUSTICA'));
alter table recebimento.remessa add column num_processo_origem varchar2(30);
alter table recebimento.remessa add column num_unico_processo varchar2(22);