export class Preferencia {
    public id: number;
    public nome: string;
    
    constructor(id: number, nome: string){
        this.id = id;
        this.nome = nome;
    }
}

export class Classe {
	public id: string;
    public nome: string;
    public preferencias: Array<Preferencia>;
    
    constructor(id: string, nome: string, preferencias: Array<Preferencia>) {
        this.id = id;
        this.nome = nome;
        this.preferencias = preferencias;
    }
}

export class Remessa {
	public classe: string;
    public qtdVolumes: number;
    public qtdApensos: number;
    public formaRecebimento: string;
    public numeroSedex: string;
    
    constructor (classe: string, qtdVolumes: number, qtdApensos: number, formaRecebimento: string, numeroSedex: string){
        this.classe = classe;
        this.qtdVolumes = qtdVolumes;
        this.qtdApensos = qtdApensos;
        this.formaRecebimento = formaRecebimento;
        this.numeroSedex = numeroSedex;
    }
}

export class Processo {
    public processoId: number;
    public remessa: Remessa;
    	
	constructor (processoId: number, remessa: Remessa) {
        this.processoId = processoId;
        this.remessa = remessa;
    }
}

export class TipoProcesso {
    public id: string;
    public nome: string;
    
    constructor(id: string, nome: string){
        this.id = id;
        this.nome = nome;
    }
}

export class TipoDocumento {
	constructor(public id: number, public descricao: string) {}
}

export interface Modelo {
	id: number;
	tipoDocumento: TipoDocumento;
	nome: string;
	documento: number;
}