
USE AVA;

-- CONSULTA OFERTAS PAGAS PELOS ALUNO
CREATE VIEW ofertasPagas
AS SELECT A.cpfAluno,H.condicao,D.idDisciplina 
FROM historico H JOIN aluno A
	ON H.cpfAluno = A.cpfAluno
    JOIN ofertadisciplina O
    ON O.idOferta =  H.idOferta
    JOIN disciplina D
    ON O.idDisciplina = d.idDisciplina
    WHERE H.condicao = 1;
    
-- CONSULTA OFERTAS , NOME DAS DISCIPLINA DAS OFERTAS E SEUS PRÉ-REQUISITOS
CREATE VIEW infoOfertas
AS SELECT O.idOferta,D.nome,D.cargaHoraria,P.requisito
FROM ofertadisciplina AS O JOIN disciplina AS D
	 ON O.idDisciplina = D.idDisciplina
	 JOIN prerequisito AS P
     ON D.idDisciplina = P.dependente;
 
-- CONSULTA ESPECIFICAÇÃO DA OFERTA
CREATE VIEW DisciplinaOferta
AS SELECT O.idOferta, D.nome, C.nome AS nome_curso, O.qtdAlunos, O.ano, O.semestre
FROM  ofertadisciplina AS O JOIN disciplina AS D
	  ON O.idDisciplina = D.idDisciplina
	  JOIN curso AS C
      ON O.idCurso = C.idCurso; 

-- CONSULTA RELAÇÃO ENTRE PROFESSOR E OFERTA
CREATE VIEW OfertaProfessor
AS SELECT O.idOferta, D.nome , P.cpfProfessor
FROM professor AS P JOIN ministraroferta AS M
	ON P.cpfProfessor = M.cpfProfessor
    JOIN ofertadisciplina AS O
    ON M.idOferta = O.idOferta
    JOIN disciplina AS D
    ON O.idDisciplina = D.idDisciplina;
  
--  CONSULTA RELAÇÃO ENTRE ALUNOS E OFERTAS 
CREATE VIEW OfertaAluno
AS SELECT H.idOferta,U.nome,U.cpf
FROM historico AS H JOIN aluno AS A
	 ON H.cpfAluno = A.cpfAluno
     JOIN usuario AS U
     ON A.cpfAluno = U.cpf;

SELECT * FROM OfertaAluno WHERE idOferta = 45;	
    
-- TESTES------------------------------------------------------------------------------------------------------------------------------------

SELECT  I.idOferta,I.nome,I.cargaHoraria FROM ofertasPagas AS O JOIN infoOfertas AS I ON O.idDisciplina =  I.requisito WHERE O.cpfAluno = '190564714-73';
SELECT * FROM DisciplinaOferta;
SELECT * FROM infoOfertas WHERE requisito = 0;