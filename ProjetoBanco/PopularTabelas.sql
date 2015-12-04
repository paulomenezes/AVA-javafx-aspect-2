USE AVA;

SET foreign_key_checks = 0;

INSERT INTO usuario(cpf,nome,foto,email,senha)VALUES('190564714-73','Rogério Duarte',NULL,'rogerio.duarte@gmail.com','1234'),
('134676398-09','Silvia Poppovick',NULL,'silviapoppovick@gmail.com','1234'),('898929087-61','Guilherme Melo',NULL,'guilherme.beberibe@gmail.com','1234'),
('345278459-19','Roger Flores',NULL,'roger_flores@gmail.com','1234'),('237812134-89','Maria José',NULL,'maria@gmail.com','1234'), 
('237453713-89','Eliel José dos Santos',NULL,'eliel.santos1@hotmail.com','1234'), ('563812908-89','Josefina Câmara',NULL,'josy.camara@gmail.com','1234'),
('153672890-03','Paulo Leonardo',NULL,'paulo.leonado@gmail.com','1234'),
('893546244-12','Miguel Falabem',NULL,'miguel.calado_eh_um_poeta@gmail.com','1234'),('782451173-93','Cristina Rocha',NULL,'crisitna.rocha@casosdefamilia.com','1234'), 
('356289457-36','César Tom Cruizzy Pereira',NULL,'cesar.cruizzy@hotmail.com','1234'), ('124673424-09','Ramón Ramayo',NULL,'ramonmayo@gmail.com','1234');

INSERT INTO  departamento(nome,idDepartamento)VALUES('DQ',1),('DF',2),('DM',3),('DEINFO',4),('DLin',5), 
('DEdF', 6);

INSERT INTO aluno(idAluno, idCurso)VALUES(1,1), (3,2),(4,4),(8,3),(9,1),(11, 4),(10,3);

INSERT INTO professor(idProfessor,idDepartamento)VALUES(12,2),(8,5),(7,3),(10,1),(2,4),(6,6),(5,5);

INSERT INTO coordenador(idCoordenador,idCurso)VALUES(12,1),(8,2),(7,3),(1,4),(10,5), (2,6), (6,7);

INSERT INTO curso(idDepartamento, nome, qtdAlunos, tipo)VALUES(2,'Física', NULL, 'Graduação'),
(3,'Matemática', NULL, 'Graduação'),(5,'Inglês', NULL, 'Pós-Graduação'),(4,'Ciência da Computação', NULL, 'Graduação'),
(1,'Química', NULL, 'Graduação'), (5,'Português', NULL, 'Graduação'),(6,'Educação Física', NULL, 'Pós-Graduação');

INSERT INTO disciplina(nome, tipo, cargaHoraria, creditos) VALUES('Cálculo I', '1', 60, 4),('Cálculo II','1', 60,4),
('Teoria da Computação','1', 60,4),('Álgebra Linear','2',30,2),('Química Inorgânica','1',60,4), ('Química Orgânica', '1', 60, 4),
('Algoritmo','1', 60,4),('Banco de Dados','1',60,4),('História da Língua Portuguesa','1',60,4), ('História da Língua Inglesa', '1', 60, 4),
('Fonemas','2', 30,2),('Geometria Analítica','1',60,4),('Física I','1',60,4), ('Física II', '1', 60, 4),
('História da Educação Física','1', 60,4), ('Mecânica Celeste','1',60,4), ('Analítica','1',60,4), 
('Psicologia', '1', 60, 4), ('Matemática LI', '1', 60, 4), ('Matemática L2', '1', 60, 4), 
('Funções', '1', 60, 4), ('Morfologia', '1', 60, 4), ('Programação I', '1', 60, 4), 
('Programação II', '1', 60, 4);

INSERT INTO disciplinaCurso(idCurso, idDisciplina, periodo) VALUES(1,1,1),(1,2,2), (1,4,1),(1,13,1), (1,14,2),
(1,16,3),(2,1,1),(2,4,1),(2,12,2),(2,16,2),(3,10,1),(3,11,1),(3,18,2),(3,22,3),(4,1,1),(4,2,2),(4,3,3),(4,4,1),
(4,7,2),(4,8,4),(4,13,3),(4,23,1),(4,24,2),(5,5,3),(5,6,4),(5,12,2),(5,13,2),(5,14,3), (5,17,2), (5,18,3),
(5,19,1),(5,20,1),(5,21,1),(6,9,1),(6,11,1),(6,18,3),(6,22,2), (7,15,1);

INSERT INTO  ofertadisciplina(idDisciplina, idCurso, qtdAlunos, ano, semestre)VALUES
(1,1,NULL,2015,1),(1,1,NULL,2014,2),(2,1,NULL,2014,1),(2,1,NULL,2015,2),(4,1,NULL,2015,1),(13,1,NULL,2015,2),
(14,1,NULL,2013,2),(1,1,NULL,2014,1),(1,1,NULL,2014,2),(1,1,NULL,2015,2),(1,2,NULL,2015,1),(1,2,NULL,2014,2),
(4,2,NULL,2015,2),(12,2,NULL,2014,2),(12,2,NULL,2015,2),(10,3,NULL,2015,2),(10,3,NULL,2014,1),(1,4,NULL,2014,2),
(2,4,NULL,2015,2),(3,4,NULL,2015,1),(4,4,NULL,2015,1),(4,4,NULL,2015,2),(7,4,NULL,2015,1), (8,4,NULL,2015,1),
(13,4,NULL,2014,2),(13,4,NULL,2015,1),(23,4,NULL,2015,1),(23,4,NULL,2015,2),(24,4,NULL,2015,1), (24,4,NULL,2015,2),
(5,5,NULL,2013,2),(5,5,NULL,2014,1),(5,5,NULL,2014,2),(5,5,NULL,2015,2),(6,5,NULL,2015,1), (6,5,NULL,2015,2),
(12,5,NULL,2014,2),(12,5,NULL,2014,1),(12,5,NULL,2015,1),(13,5,NULL,2015,1),(14,5,NULL,2015,1), (14,5,NULL,2015,2),
(17,5,NULL,2013,1),(17,5,NULL,2013,2),(17,5,NULL,2014,1),(17,5,NULL,2014,2),(17,5,NULL,2015,1), (17,5,NULL,2015,2),
(18,5,NULL,2013,1),(18,5,NULL,2013,2),(18,5,NULL,2014,1),(18,5,NULL,2014,2),(18,5,NULL,2015,1), (18,5,NULL,2015,2),
(19,5,NULL,2013,1),(19,5,NULL,2013,2),(19,5,NULL,2014,1),(19,5,NULL,2014,2),(19,5,NULL,2015,1), (19,5,NULL,2015,2),
(20,5,NULL,2013,1),(20,5,NULL,2013,2),(20,5,NULL,2014,1),(20,5,NULL,2014,2),(20,5,NULL,2015,1), (20,5,NULL,2015,2),
(21,5,NULL,2013,1),(21,5,NULL,2013,2),(21,5,NULL,2014,1),(21,5,NULL,2014,2),(21,5,NULL,2015,1), (21,5,NULL,2015,2),
(9,6,NULL,2013,1),(9,6,NULL,2013,2),(9,6,NULL,2014,1),(9,6,NULL,2014,2),(9,6,NULL,2015,1), (9,6,NULL,2015,2),
(11,6,NULL,2013,1),(11,6,NULL,2013,2),(11,6,NULL,2014,1),(11,6,NULL,2014,2),(11,6,NULL,2015,1), (11,6,NULL,2015,2),
(18,6,NULL,2013,1),(18,6,NULL,2013,2),(18,6,NULL,2014,1),(18,6,NULL,2014,2),(18,6,NULL,2015,1), (18,6,NULL,2015,2),
(22,6,NULL,2013,1),(22,6,NULL,2013,2),(22,6,NULL,2014,1),(22,6,NULL,2014,2),(22,6,NULL,2015,1), (22,6,NULL,2015,2),
(15,7,NULL,2013,1),(15,7,NULL,2013,2),(15,7,NULL,2014,1),(15,7,NULL,2014,2),(15,7,NULL,2015,1), (15,7,NULL,2015,2)
;

INSERT INTO diaHoraOfertaDisciplina(idOferta, dia, horario) VALUES(1,'seg','16:00'),(1,'qua','14:00'),(2,'seg','10:00'),(2,'qui','08:00'),
(3,'seg','14:00'),(3,'qua','14:00'),(4,'ter','10:00'),(4,'qui','08:00'),(5,'seg','14:00'),(5,'qua','16:00'),(6,'seg','08:00'),(6,'qui','10:00'),
(7,'qua','16:00'),(7,'sex','14:00'),(9,'seg',8),(10,'qui',8),(11,'ter',16),(11,'sex', '14:00'),(12,'seg','10:00'),(12,'sex','08:00'),
(20,'seg','20:00'),(23,'qua','18:00'),(24,'ter','18:00'),(25,'qui','08:00'),(26,'ter','14:00'),(27,'ter','16:00'),(27,'seg','08:00'),(28,'qui','10:00'),
(29,'seg','16:00'),(30,'sex','14:00'),(34,'ter','10:00'),(40,'ter','08:00'),(45,'seg','16:00'),(50,'sex','14:00'),(52,'qui','20:00'),(46,'ter','20:00');

INSERT INTO prerequisito(idCurso, dependente,requisito) VALUES(1,1,1),(1,2,1), (1,4,4),(1,13,13), (1,14,13),
(1,16,13),(2,1,1),(2,4,4),(2,12,12),(2,16,13),(3,10,10),(3,11,11),(3,18,18),(3,22,22),(4,1,1),(4,2,1),(4,3,3),(4,4,4),
(4,7,1),(4,8,24),(4,8,7),(4,13,2),(4,23,23),(4,24,23),(5,5,5),(5,6,6),(5,12,12),(5,13,20),(5,13,21),(5,14,13), 
(5,17,17), (5,18,18),(5,19,19),(5,20,19),(5,21,21),(6,9,9),(6,11,11),(6,18,18),(6,22,11), (7,15,15);

INSERT INTO ministrarOferta(idOferta, idProfessor)VALUES(1,7),(2,7),(5,8),(7,12),(8,8),(19,5),(22,5),(56,2),
(34,12),(35,2),(33,10),(25,8),(37,7),(44,10),(45,2),(38,6),(52,12),(53,2),(43,12),(46,2),(54,10),(55,12),(12,8),(3,7);

INSERT INTO matricular(idAluno, idOferta, dataMatricula, numeroProtocolo)VALUES(1,1,NULL,12),(1,4,NULL,11),
(1,5,NULL,13),(1,6,NULL,9),(3,5,NULL,2),(3,4,NULL,3),(3,45,NULL,123),(4,4,NULL,21),(4,13,NULL,34),(4,5,NULL,90),
(9,8,NULL,278),(8,8,NULL,134),(3,9,NULL,33),(8,4,NULL,27),(9,45,NULL,109),(9,4,NULL,103),(11,33,NULL,35),(11,6,NULL,132);

INSERT INTO nota(idAluno, idOferta, nota1, nota2, nota3, notaFinal)VALUES(1,1,3,7,9, NULL),(1,4,9,9,NULL,NULL),
(1,5,3,5,7, NULL),(1,6,9,9,NULL,NULL),(3,5,8,4,7,NULL),(3,4,2,4.5,6.7,5),(3,45,5.5,6,7.5, 7.5),(4,4,5,8,9, NULL),
(4,13,7.5,7.5,8, NULL),(4,5, 5.0,5.0,3,2),(8,5, 9, 6, 8.5, NULL),(8,8, 4.5,5.5,9, NULL),(8,5,5,9,NULL,NULL),
(8,4,3.5,4.5,7.0, 7),(9,45,7,7,NULL,NULL),(9,4,5,6.5,8.5, NULL), (11,33, 7,6,7, NULL),(11,6,5.0,3.5,9.5, NULL);

INSERT INTO historico(idAluno, idOferta, condicao, media)VALUES(1,1,NULL,NULL),(1,4,NULL,NULL),
(1,5,NULL,NULL),(1,6,NULL,NULL),(3,5,NULL,NULL),(3,4,NULL,NULL),(3,45,NULL,NULL),(4,4,NULL,NULL),(4,13,NULL,NULL),(4,5,NULL,NULL),
(9,8,NULL,NULL),(8,8,NULL,NULL),(3,9,NULL,NULL),(8,4,NULL,NULL),(9,45,NULL,NULL),(9,4,NULL,NULL),(11,33,NULL,35),(11,6,NULL,NULL);


INSERT INTO projetopesquisa(nome, modalidade, organizacao)VALUES('proj1','PIBIC', 'CNPq'),('proj2','PIBID', 'CNPq'),
('proj3','PIBID', 'CNPq'),('proj4','PICME', 'FACEPE'),('proj5','PICME', 'FACEPE');

INSERT INTO projetoProfessor(idProfessor, idProjeto) VALUES(8,1),(12,2),(12,1),(7,3),(6,5),(8,5);
INSERT INTO projetoAluno(idProjeto, idAluno) VALUES(1,3),(1,4),(2,9),(1,1),(3,10),(4,10),(5,9);
INSERT INTO solicitacaoProjeto(idAluno, idProjeto, estado)VALUES(11,1,false),(11,3,false),
(11,5,true),(4,3,false),(4,3,true);


INSERT INTO artigo(idProjeto, nome, tema, objetivo, area)VALUES(1,'Mecânica Quântica','GH','Nada','Física'),
(2,'Formas de Elucidar a Física','GH', 'Deboinha','Física'),(3,'Computação Quântica','GH','Só na mamata','Computação'),
(4, 'Mecânica Celeste','GH', 'Fazer nada','Matemática'),(5,'Triângulo de Newton','GH', 'Dormir', 'Matemática');


-- INSERT INTO atividade(tipo,nome,codOferta,descricao,dataEntrega)VALUES('1','1',1,NULL,NULL),('2','2',2,NULL,NULL),
-- ('3','3',3,NULL,NULL),('4','4',5,NULL,NULL),('5','5',5,NULL,NULL);

-- INSERT INTO realizaratividade(atividadeNome,cpfAluno,nota)VALUES('1','10',6),('1','12',6),('2','10',6),('3','13',6),('3','14',6);

-- INSERT INTO aviso(titulo,dataEnvio,prioridade,horaEnvio,idAviso)VALUES('menssagem1',NULL,2,NULL,1),('menssagem2',NULL,2,NULL,2),
-- ('menssagem3',NULL,2,NULL,3),('menssagem4',NULL,2,NULL,4),('menssagem5',NULL,2,NULL,5);

-- INSERT INTO disponibaviso(cpfUsuario,codOferta,idAviso)VALUES('10',1,1),('11',2,2),('11',3,3),('13',3,3),('14',4,4);

-- INSERT INTO forum(codForum, titulo, descricao, codCriador)VALUES(1,'problema1','problema1','10'),(2,'problema2','problema2','10'),
-- (3,'problema1','problema1','14'),(4,'problema1','problema1','14'),(5,'problema5','problema5','11');

-- INSERT INTO forumusuarioparticipar(rcodForum, rcodUsuario)VALUES(1,'10'),(1,'11'),(2,'13'),(2,'14'),(5,'14');

-- INSERT INTO materialdisciplina(codigo, fonte, tipo, arquivo, codOfertaDisc, codProf)VALUES(1,'a','b',NULL,1,'10'),(2,'a','b',NULL,1,'10'),
-- (3,'a','b',NULL,1,'10'),(4,'a','b',NULL,1,'10'),(5,'a','b',NULL,1,'10');

-- INSERT INTO tag(codForum,nome,tamanho)VALUES(1,'abrir',10),(2,'abrir',10),(3,'abrir',10),(4,'abrir',10),(5,'abrir',10);

-- INSERT INTO mediasperiodo(codHistorico,periodo)VALUES('10',2),('11',2),('12',2),('13',2),('14',2);

-- UPDATE mediasperiodo SET media = 2.3 WHERE codHistorico ='10';

-- UPDATE mediasperiodo SET media = 2.3 WHERE codHistorico ='11';

-- UPDATE mediasperiodo SET media = 2.3 WHERE codHistorico ='12';

-- UPDATE mediasperiodo SET media = 2.3 WHERE codHistorico ='13';

-- UPDATE mediasperiodo SET media = 2.3 WHERE codHistorico ='14';

SET foreign_key_checks = 1;
