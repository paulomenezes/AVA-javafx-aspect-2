
USE AVA;

CREATE VIEW ofertasPagas
AS SELECT A.cpfAluno,H.condicao,D.idDisciplina 
FROM historico H JOIN aluno A
	ON H.cpfAluno = A.cpfAluno
    JOIN ofertadisciplina O
    ON O.idOferta =  H.idOferta
    JOIN disciplina D
    ON O.idDisciplina = d.idDisciplina
    WHERE H.condicao = 1;
    

CREATE VIEW infoOfertas
AS SELECT O.idOferta,D.nome,D.cargaHoraria,P.requisito
FROM ofertadisciplina AS O JOIN disciplina AS D
	 ON O.idDisciplina = D.idDisciplina
	 JOIN prerequisito AS P
     ON D.idDisciplina = P.dependente;
 

CREATE VIEW DisciplinaOferta
AS SELECT O.idOferta, D.nome, C.nome AS nome_curso, O.qtdAlunos, O.ano, O.semestre
FROM  ofertadisciplina AS O JOIN disciplina AS D
	  ON O.idDisciplina = D.idDisciplina
	  JOIN curso AS C
      ON O.idCurso = C.idCurso; 


     
-- TESTES------------------------------------------------------------------------------------------------------------------------------------

SELECT  I.idOferta,I.nome,I.cargaHoraria FROM ofertasPagas AS O JOIN infoOfertas AS I ON O.idDisciplina =  I.requisito WHERE O.cpfAluno = '190564714-73';
SELECT * FROM DisciplinaOferta;
SELECT * FROM infoOfertas WHERE requisito = 0;