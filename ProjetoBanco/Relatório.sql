use ava;
SELECT c.nome AS NomeCurso, c.tipo AS Tipo, d.nome AS NomeDisciplina, d.cargaHoraria, d.creditos, dept.nome AS NomeDepartamento 
	FROM ofertaDisciplina AS od 
	INNER JOIN disciplina AS d ON od.idDisciplina = d.idDisciplina
    INNER JOIN curso AS c ON od.idCurso = c.idCurso
    INNER JOIN departamento AS dept ON c.idDepartamento = dept.idDepartamento
    ORDER BY NomeCurso;
   
SELECT d.nome AS NomeDisciplina, c.nome AS NomeCurso, u.nome AS NomeAluno, IF(h.condicao = 0, 'Reprovado', 'Aprovado') AS Condicao  	
	FROM historico AS h  	
    INNER JOIN ofertadisciplina AS o ON h.idOferta = o.idOferta      
    INNER JOIN disciplina AS d ON o.idDisciplina = d.idDisciplina     
    INNER JOIN curso AS c ON o.idCurso = c.idCurso     
    INNER JOIN usuario AS u ON h.cpfAluno = u.cpf;

SELECT u.nome AS Aluno, p.nome AS NomeProjeto, p.modalidade, p.organizacao FROM participarProjeto AS pp 
	INNER JOIN usuario AS u ON pp.cpfAluno = u.cpf 
    INNER JOIN projetoPesquisa AS p ON pp.idProjeto = p.idProjeto;