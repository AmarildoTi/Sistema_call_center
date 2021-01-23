function validaCPF (cpf){
    var numeros, digitos, soma, i, resultado, digitos_iguais;
    digitos_iguais = 1;
    cpf = cpf.replace("-", "");
    cpf = cpf.replace(".", "");
    cpf = cpf.replace(".", "");
    if (cpf.length < 11)
        return false;
    for (i = 0; i < cpf.length - 1; i++)
        if (cpf.charAt(i) != cpf.charAt(i + 1)){
			digitos_iguais = 0;
            break;
        }
    if (!digitos_iguais){
        numeros = cpf.substring(0,9);
        digitos = cpf.substring(9);
        soma = 0;
        for (i = 10; i > 1; i--)
            soma += numeros.charAt(10 - i) * i;
        
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        
        if (resultado != digitos.charAt(0))
            return false;
            
		numeros = cpf.substring(0,10);
        soma = 0;
        
		for (i = 11; i > 1; i--)
			soma += numeros.charAt(11 - i) * i;

		resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(1))
            return false;
        return true;
	}	
    else
        return false;
}

function validaCNPJ(cnpj){
    var numeros, digitos, soma, i, resultado, pos, tamanho, digitos_iguais;
    digitos_iguais = 1;
    cnpj = cnpj.replace("-", "");
    cnpj = cnpj.replace("/", "");
    cnpj = cnpj.replace(".", "");
    cnpj = cnpj.replace(".", "");

    if (cnpj.length < 14 && cnpj.length < 15)
        return false;
    for (i = 0; i < cnpj.length - 1; i++)
        if (cnpj.charAt(i) != cnpj.charAt(i + 1)){
            digitos_iguais = 0;
            break;
        }
    
    if (!digitos_iguais){
        tamanho = cnpj.length - 2
        numeros = cnpj.substring(0,tamanho);
        digitos = cnpj.substring(tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--){
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                pos = 9;
        }
        
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        
        if (resultado != digitos.charAt(0))
            return false;
        tamanho = tamanho + 1;
        numeros = cnpj.substring(0,tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--){
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                pos = 9;
        }
        
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        
        if (resultado != digitos.charAt(1))
            return false;
        
        return true;
    }
    else
        return false;
} 

function somenteNumeros(e){
    var tecla=(window.event)?event.keyCode:e.which;   
    if((tecla>47 && tecla<58)) return true;
    else{
    	if (tecla==8 || tecla==0) return true;
	else  return false;
    }
}

function semCaracteresEspeciais(e){
    var tecla=(window.event)?event.keyCode:e.which;   
    if((tecla>47 && tecla<58)) return true;
	else
	    if((tecla>64 && tecla<91)) return true;
	else
	    if((tecla>96 && tecla<123)) return true;
	else
            if (tecla==8 || tecla==0 || tecla == 32) return true;
        else
            return false;
}

function maximoCaracteres(objeto,max){
    return (objeto.value.length <= max);
}

function mudaAction(x,y,z){
    document.form.action=x;
    document.form.operacao.value=y;
    document.form.jsp.value=z;
    document.form.submit();
}

function calendario(x,y,z){
    $(x).datepicker({
		closeText: 'Fechar',
		prevText: 'Anterior',
		nextText: 'Pr&oacute;ximo',
		currentText: 'Hoje',
		monthNames: ['Janeiro','Fevereiro','Mar&ccedil;o','Abril','Maio','Junho',
		'Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun',
		'Jul','Ago','Set','Out','Nov','Dez'],
		dayNames: ['Domingo','Segunda-feira','Ter&ccedil;a-feira','Quarta-feira','Quinta-feira','Sexta-feira','S&aacute;bado'],
		dayNamesMin: ['D','S','T','Q','Q','S','S'],
		dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S&aacute;b'],
		weekHeader: 'Sm',
		dateFormat: 'dd/mm/yy',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: '',
                minDate: y, 
                maxDate: z,
                beforeShowDay: $.datepicker.noWeekends});
}

function isDate(txtDate){
    var currVal = txtDate;
    if(currVal == '')
        return false;
    
    //Declare Regex 
    var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
    var dtArray = currVal.match(rxDatePattern); // is format OK?
    if (dtArray == null)
        return false;
    
    //Checks for mm/dd/yyyy format.
    dtDay = dtArray[1];
    dtMonth= dtArray[3];
    dtYear = dtArray[5];
    
    if (dtMonth < 1 || dtMonth > 12)
        return false;
    else if (dtDay < 1 || dtDay> 31)
        return false;
    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
        return false;
    else if (dtMonth == 2){
        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
        if (dtDay> 29 || (dtDay ==29 && !isleap))
            return false;
    }
    return true;
}

function calcularOpcaoNegociacao(){
    document.form.action='//localhost:8084/CallCenter/mvc';
    document.form.operacao.value='OpcaoNegociacaoCalcular';
    document.form.submit();
}

function concluirNegociacao(){
    document.form.action='//localhost:8084/CallCenter/mvc';
    document.form.operacao.value='NegociacaoConcluir';
    document.form.submit();
}