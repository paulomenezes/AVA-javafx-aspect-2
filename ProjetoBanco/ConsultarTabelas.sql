SELECT * FROM usuario;
SELECT * FROM aluno;
SELECT * FROM professor;
SELECT * FROM disciplina;
SELECT * FROM curso;
SELECT * FROM departamento;

-- Lista todos os nomes de todos os alunos com seu curso, tipo, foto e email
SELECT 
	u.nome as NomeAluno, 
    c.nome as NomeCurso, 
    a.tipo, u.foto, u.email 
FROM aluno AS a 
		JOIN curso AS c ON a.codCurso = c.codigo 
        JOIN usuario AS u ON a.cpf = u.cpf;

-- Lista todos os nomes de todos os alunos com seu curso, tipo, foto, email e o departamento do seu curso
SELECT 
    u.nome AS NomeAluno,
    a.tipo, u.foto, u.email,
    c.nome AS NomeCurso,
    d.nome as NomeDepartamento
FROM aluno AS a
        JOIN curso AS c ON a.codCurso = c.codigo
        JOIN usuario AS u ON a.cpf = u.cpf
        JOIN departamento AS d ON c.idDepartamento = d.idDepartamento;

-- Lista todas as disciplinas com seu nome, carga horária, tipo, semestre, período, quantidaded e aluno e o curso qual pertence de um professor específico 
SELECT 
	d.nome AS NomeDisciplina,
    d.tipo, d.cargaHor,
    oD.qtdAlunos, oD.semestre, oD.periodo,
    c.nome as NomeCurso
FROM disciplina AS d
		JOIN curso AS c ON d.codCurso = c.codigo
		JOIN ofertaDisciplina AS oD ON d.codigo = oD.codDisc
        JOIN ministrarOferta AS mO ON oD.codDisc = mO.codOfertaDisciplina
        WHERE mO.cpfProfessor = '10';
        
-- Lista todos os requisitos de uma disciplina
SELECT
	requisito.nome, requisito.cargaHor
FROM preRequesito AS pR 
		JOIN disciplina AS requisito ON pr.requesito = requisito.codigo
        WHERE pR.dependente = '1';

-- Lista todos os cursos, o nome do seu departamento e do seu coordenador
SELECT
	c.nome as NomeCurso,
    d.nome as NomeDepartamento,
    u.nome as NomeCoordenador
FROM curso AS c
		JOIN departamento AS d ON c.idDepartamento = d.idDepartamento
		JOIN coordenador AS cc ON c.codigo = cc.codCurso
        JOIN usuario AS u ON cc.cpf = u.cpf;

-- Lista todas as atividades feita por um aluno específico junto com a disciplina da atividade
SELECT 
	a.nome AS NomeAtividade,
    a.descricao AS DescricaoAtividade,
    a.dataEntrega AS DataEntrega,
    oD.semestre, oD.periodo,
    d.nome AS NomeDisciplina,
    c.nome AS NomeCurso
FROM atividade AS a
		JOIN realizarAtividade AS rA ON a.nome = rA.atividadeNome
        JOIN ofertaDisciplina AS oD ON a.codOferta = oD.codDisc
        JOIN disciplina AS d ON oD.codDisc = d.codigo
        JOIN curso AS c ON d.codCurso = c.codigo
        WHERE rA.cpfAluno = '10';