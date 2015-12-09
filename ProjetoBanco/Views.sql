
USE AVA;

CREATE VIEW requisitos
AS SELECT O.idOferta,D.nome,D.cargaHoraria,U.nome AS nome_professor
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
