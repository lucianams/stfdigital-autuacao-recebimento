System.config({
	transpiler: null,
	defaultJSExtensions: true,
	map: {
		'systemjs': './node_modules/systemjs/dist/system.src.js',
		'system-polyfills': './node_modules/systemjs/dist/system-polyfills.src.js'
	},
	bundles: {
		'recebimento/bundle': ['recebimento/preparar-oficio-devolucao/*', 'recebimento/devolucao-assinatura/*', 'recebimento/peticoes-fisicas/*', 'recebimento/preautuacao/originario/*', 'recebimento/services/*']
	}
});