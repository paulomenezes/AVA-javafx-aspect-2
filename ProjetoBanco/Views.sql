
USE AVA;

CREATE VIEW ofertasPagas
AS SELECT A.cpfAluno,H.idOferta,H.condicao 
FROM aluno AS A JOIN historico AS H 
	 ON A.cpfAluno = H.cpfAluno
     WHERE H.condicao = 1;
 
CREATE VIEW requisitos
AS SELECT O.idOferta,R.requisito,D.nome,D.cargaHoraria,U.nome AS nome_professor
FROM ofertadisciplina AS O JOIN prerequisito AS R
	 ON O.idDisciplina = R.dependente
     JOIN disciplina AS D
     ON D.idDisciplina = O.idDisciplina
     JOIN ministraroferta AS M 
     ON M.idOferta = O.idOferta
     JOIN professor AS P 
     ON P.cpfProfessor = M.cpfProfessor
     JOIN usuario AS U 
     ON U.cpf = P.cpfProfessor;
     
     
-- TESTES------------------------------------------------------------------------------------------------------------------------------------

SELECT  * FROM  requisitos;

SELECT * FROM ofertasPagas;


SELECT O.idOferta, D.nome
FROM ofertaDisciplina O JOIN disciplina D
	ON O.idDisciplina = D.idDisciplina;
    
    
SELECT R.idOferta,R.nome, R.cargaHoraria,R.nome_professor FROM ofertasPagas AS A JOIN requisitos AS R ON A.idOferta = R.requisito WHERE A.cpfAluno = '190564714-73' GROUP BY R.idOferta;