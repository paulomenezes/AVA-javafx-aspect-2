-- GERAR MÉDIA DE UM ALUNO NUMA OFERTA
-- ENTREGO O ID DA OFERTA E CALCULA A MÉDIA DE CADA ALUNO DA OFERTA
use ava;
-- enviar de novo
delimiter |
create procedure calcularMediaDeCadaAlunoNaOferta(idOf int)
	begin
	-- parte dos cursores
		declare done int default 0;
        declare idA varchar(14);-- alunoLinha
        declare idO int; -- ofertaLinha
        declare notaP double; -- 1 va Primeira
        declare notaS double; -- 2 va Segunda
        declare notaT double; -- 3 va Terceira
        declare notaFinal double; -- final


     -- variáveis auxiliares no cálculo
        declare soma double;
        declare mediaP double; -- media parcial
        declare mediaF double; -- media final
        declare situacao varchar(25);

     -- cursores e suas definições
		declare notaCursor cursor for select cpfAluno, idOferta, nota1, nota2, nota3, notaFinal from nota;
		declare continue handler for not found set done = 1;

		open notaCursor;
		repeat
			fetch notaCursor into idA, idO, notaP, notaS, notaT, notaFinal;
			if (idO = idOf) then
            
					if (notaP is not null and notaS is not null) then
						set soma = notaP + notaS;
						set mediaP = soma/2;
					else set mediaP = 0; -- se não fez as duas primeiras provas, está reprovado, média 0
                    
					end if;

					if (notaT is not null) then 
                    -- se notaT é dif de null é porque tem alguma nota: 0,1,2,...10
						if (notaP is not null and notaS is not null) then 
                        -- testa se são diferentes de null para poder comparar e não dá nenhum erro
            	-- se os 3 são diferentes de null (fez as 3 provas) tem de achar os dois maiores para somá-los
							if ((notaP > notaS and notaS > notaT) or (notaP > notaT and notaS > notaT) or
                            (notaS > notaP and notaP > notaT) or (notaS > notaT and notaP > notaT)) then 
								set soma = notaP + notaS;
                                
							elseif ((notaP > notaS and notaT > notaS) or (notaP > notaT and notaT > notaS) or
                            (notaT > notaS and notaP > notaS) or (notaT> notaP and notaP > notaS)) then
								set soma = notaP + notaT;
                                
							elseif ((notaS > notaP and notaT > notaP) or (notaS > notaT and notaT > notaP)
                            or (notaT > notaS and notaS > notaP) or (notaT > notaP and notaS > notaP)) then
								set soma = notaS + notaT;

							end if;
						elseif(notaP is not null and notaS is null) then -- não fez a segunda prova, basta somar a primeira com a terceira
							set soma = notaT + notaP;
						else if (notaS is not null and notaP is null) then -- não fez a primeira prova, basta somar a segunda com a terceira
							set soma = notaS + notaT;
						end if;
						set mediaP = soma/2;
					end if;
                
					end if;
                    
                    if (notaFinal is not null) then -- fez final, logo tem de somá-la com a média obtida anteriormente, senão (não fez), basta pegar a média calculada antes
						set mediaF = (notaFinal + mediaP)/2;
                        
						if (mediaF >= 5) then -- média da final
							set situacao = 'aprovado';
						else 
							set situacao = 'reprovado';
						end if;
                        
					else 
						set mediaF = mediaP;
                        
						if (mediaF >= 7) then -- média para passar de até 3ª va
							set situacao = 'aprovado por média';
						else
							set situacao = 'reprovado';
						end if;
                        
					end if;
                    
				update historico
					set condicao = situacao,
						media = mediaF
                    where (idOferta = idO and cpfAluno = idA);-- após feito o calculo da média do aluno, temos de pô-la na tabela de historico
			end if;
			until done
			end repeat;
            close notaCursor;
	end
	|