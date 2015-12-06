SELECT * FROM ava.historico;

CREATE VIEW  alunoHistorico
AS SELECT A.cpfAluno,H.idOferta 
FROM aluno AS A JOIN historico AS H 
	 ON A.cpfAluno = H.cpfAluno;
 
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

SELECT  * From aluno;
    
    
 SELECT R.nome, R.cargaHoraria,R.nome_professor FROM alunoHistorico AS A JOIN requisitos AS R ON A.idOferta = R.requisito WHERE A.cpfAluno = '123';