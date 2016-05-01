
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
-- só para enviar de novo
-- -----------------------------------------------------
-- Schema ava
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ava` DEFAULT CHARACTER SET utf8 ;
USE `ava` ;

-- -----------------------------------------------------
-- Table `ava`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`usuario` (
	
  `cpf` VARCHAR(14) NOT NULL UNIQUE ,
  `nome` VARCHAR(40) NOT NULL COMMENT '',
  `foto` LONGBLOB NULL DEFAULT NULL COMMENT '',
  `email` VARCHAR(40) NOT NULL COMMENT '',
  `senha` VARCHAR(20) NOT NULL COMMENT '',
   `tipo`  INT(4) NOT NULL DEFAULT 0,		
  PRIMARY KEY (`cpf`))
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`departamento` (
  `nome` VARCHAR(20) NOT NULL COMMENT '',
  `idDepartamento` INT AUTO_INCREMENT NOT NULL COMMENT '',
  PRIMARY KEY (`idDepartamento`))
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`curso` (
  `idCurso` INT AUTO_INCREMENT NOT NULL COMMENT '',
  `idDepartamento` INT NOT NULL COMMENT '',
  `nome` VARCHAR(30) NOT NULL COMMENT '',
  `qtdAlunos` INT(3) DEFAULT 0 COMMENT '',
  `tipo` VARCHAR(25) NOT NULL COMMENT '',
  PRIMARY KEY (`idCurso`),
	INDEX `fk_departamento` (`idDepartamento` ASC),
    FOREIGN KEY (`idDepartamento`)
    REFERENCES `ava`.`departamento` (`idDepartamento`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ava`.`disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`disciplina` (
  `idDisciplina` INT AUTO_INCREMENT NOT NULL COMMENT '',
  `nome` VARCHAR(35) NOT NULL COMMENT '',
  `tipo` VARCHAR(20) NOT NULL COMMENT '',
  `cargaHoraria` INT(4) NOT NULL COMMENT '',
  `creditos` INT(4) NOT NULL COMMENT '',
	PRIMARY KEY (`idDisciplina`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`ofertadisciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`ofertaDisciplina` (
  `idOferta`	INT AUTO_INCREMENT NOT NULL,
  `idDisciplina` INT NOT NULL COMMENT '',
  `idCurso` INT NOT NULL COMMENT '',
  `qtdAlunos` INT(3) DEFAULT 0 COMMENT '',
  `ano` INT NOT NULL COMMENT '',
  `semestre` INT(11) NOT NULL COMMENT '',
  
  PRIMARY KEY (`idOferta`,`idDisciplina`, `idCurso`),
    FOREIGN KEY (`idDisciplina`)
    REFERENCES `ava`.`disciplina` (`idDisciplina`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idCurso`)
    REFERENCES `ava`.`curso` (`idCurso`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`disciplinaCurso` (como se fosse grade curricular)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`disciplinaCurso` (
   `idCurso` INT NOT NULL COMMENT '',
   `idDisciplina` INT NOT NULL COMMENT '',
    `periodo`  INT NOT NULL COMMENT '', -- período na qual por padrão ela deveria ser paga, a título de info
	`ementa` LONG VARCHAR COMMENT '', 
PRIMARY KEY (`idCurso`, `idDisciplina`),
	FOREIGN KEY (`idCurso`)
    REFERENCES `ava`.`curso` (`idCurso`),
    FOREIGN KEY (`idDisciplina`)
    REFERENCES `ava`.`disciplina` (`idDisciplina`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ava`.`aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`aluno` (
  `cpfAluno` VARCHAR(14) NOT NULL,
  `idCurso` INT(4) DEFAULT NULL COMMENT '',
  PRIMARY KEY (`cpfAluno`),
    FOREIGN KEY (`cpfAluno`)
    REFERENCES `ava`.`usuario` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idCurso`)
    REFERENCES `ava`.`curso` (`idCurso`)
    ON DELETE SET NULL
    ON UPDATE SET NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`professor` (
  `cpfProfessor` VARCHAR(14) NOT NULL,
   `idDepartamento` INT(4) NOT NULL,
   PRIMARY KEY(`cpfProfessor`),
   FOREIGN KEY(`cpfProfessor`)
   REFERENCES ava.usuario(`cpf`),
   FOREIGN KEY(`idDepartamento`)
   REFERENCES ava.departamento(`idDepartamento`)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ava`.`projetoPesquisa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`projetoPesquisa` (
  `idProjeto` INT AUTO_INCREMENT NOT NULL COMMENT '',
  `nome` VARCHAR(35) NOT NULL COMMENT '',
  `modalidade` VARCHAR(10) NOT NULL COMMENT '',
  `organizacao` VARCHAR(20) NOT NULL COMMENT '',
`valorBolsa` DOUBLE NOT NULL COMMENT '',
`nVagas` INT(2) NOT NULL COMMENT '',
  PRIMARY KEY (`idProjeto`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`participarprojeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`participarProjeto` (
  `cpfAluno` VARCHAR(14) NOT NULL ,
  `idProjeto` INT NOT NULL COMMENT '',
  PRIMARY KEY (`cpfAluno`,`idProjeto`),
    FOREIGN KEY (`cpfAluno`)
    REFERENCES `ava`.`aluno` (`cpfAluno`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `participarprojeto_ibfk_2`
	FOREIGN KEY (`idProjeto`)
    REFERENCES `ava`.`projetoPesquisa` (`idProjeto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -------------------------------------------------
-- Table `ava`.projetoProfessor`
-- -------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`projetoProfessor` (
  `cpfProfessor` VARCHAR(14) NOT NULL,
  `idProjeto` INT NOT NULL ,
  PRIMARY KEY (`cpfProfessor`,`idProjeto`),
	FOREIGN KEY (`idProjeto`)
    REFERENCES `ava`.`projetoPesquisa`(`idProjeto`),
    FOREIGN KEY (`cpfProfessor`)
    REFERENCES `ava`.`professor` (`cpfProfessor`)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`artigo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`artigo` (
  `idArtigo` INT AUTO_INCREMENT NOT NULL,
  `idProjeto` INT NOT NULL DEFAULT 0 COMMENT '',
  `nome` VARCHAR(40) NOT NULL COMMENT '',
  `tema` VARCHAR(35) NOT NULL COMMENT '',
  `objetivo` LONGTEXT NULL DEFAULT NULL COMMENT '',
  `area` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`idArtigo`),
    FOREIGN KEY (`idProjeto`)
    REFERENCES `ava`.`projetopesquisa` (`idProjeto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ava`.`atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`atividade` (
  `idAtividade`	INT AUTO_INCREMENT NOT NULL,
  `idOferta` INT NOT NULL COMMENT '',
  `nome` VARCHAR(20) NOT NULL COMMENT '',
  `descricao` LONGTEXT NULL DEFAULT NULL COMMENT '',
  `tipo` VARCHAR(11) NOT NULL COMMENT '',
  `dataEntrega` DATE NULL DEFAULT NULL COMMENT '',
	PRIMARY KEY (`idAtividade`),
    FOREIGN KEY (`idOferta`)
    REFERENCES `ava`.`ofertadisciplina` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`aviso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`aviso` (
  `idAviso` INT AUTO_INCREMENT NOT NULL COMMENT '',
  `idRemetente` VARCHAR(14) DEFAULT NULL COMMENT '',
  `titulo` VARCHAR(40) NOT NULL COMMENT '',
  `descricao` TEXT DEFAULT NULL COMMENT '',
  `prioridade` INT(3) DEFAULT NULL COMMENT '', -- -1: baixa, 0: média, 1:alta
  `dataEnvio` DATE NULL DEFAULT NULL COMMENT '',
  `horaEnvio` TIME NULL DEFAULT NULL COMMENT '',
  `idDestinatarioO` INT(10) DEFAULT NULL COMMENT '', -- direcionado a uma oferta de disciplina
  `idDestinatarioU` VARCHAR(14) DEFAULT NULL COMMENT '', -- direcionado a uma pessoa
  
  PRIMARY KEY (`idAviso`),
    FOREIGN KEY (`idRemetente`)
    REFERENCES `ava`.`usuario` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idDestinatarioO`)
    REFERENCES `ava`.`ofertadisciplina` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idDestinatarioU`)
    REFERENCES `ava`.`usuario` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ava`.`coordenador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`coordenador` (
  `cpfCoordenador` VARCHAR(14) NOT NULL,
  `idCurso` INT NOT NULL COMMENT '',
	PRIMARY KEY (`cpfCoordenador`),
    FOREIGN KEY (`cpfCoordenador`)
    REFERENCES `ava`.`professor` (`cpfProfessor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idCurso`)
    REFERENCES `ava`.`curso` (`idCurso`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`diaHoraOfertadisciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`diaHoraOfertaDisciplina` (
  `idOferta` INT NOT NULL COMMENT '',
  `dia` VARCHAR(10) NOT NULL COMMENT '',
  `horario` TIME NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idOferta`, `horario`, `dia`),
    FOREIGN KEY (`idOferta`)
    REFERENCES `ava`.`ofertaDisciplina` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`historico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`historico` (
  `cpfAluno` VARCHAR(14) NOT NULL,
   `idOferta` INT NOT NULL,
   `condicao` VARCHAR(25) DEFAULT NULL,
  `media` DOUBLE  DEFAULT NULL COMMENT '',
	PRIMARY KEY (`cpfAluno`, `idOferta`),
    FOREIGN KEY (`cpfAluno`)
    REFERENCES `ava`.`aluno` (`cpfAluno`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idOferta`)
    REFERENCES `ava`.`ofertaDisciplina` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ava`.`disponibilizarAviso`
-- -----------------------------------------------------
-- CREATE TABLE IF NOT EXISTS `ava`.`disponibilizarAviso` (
  -- `idAviso` INT(11) NOT NULL COMMENT '',
  -- `cpfUsuario` VARCHAR(14) NOT NULL ,
  -- `idOferta` INT NOT NULL COMMENT '',
	-- PRIMARY KEY (`cpfUsuario`, `idOferta`, `idAviso`),
--    FOREIGN KEY (`cpfUsuario`)
  --  REFERENCES `ava`.`usuario` (`cpf`)
   -- ON DELETE CASCADE
    -- ON UPDATE CASCADE,
  -- CONSTRAINT `disponibaviso_ibfk_3`
    -- FOREIGN KEY (`idOferta`)
    -- REFERENCES `ava`.`ofertaDisciplina` (`idOferta`)
    -- ON DELETE CASCADE
    -- ON UPDATE CASCADE,
  -- CONSTRAINT `disponibaviso_ibfk_4`
    -- FOREIGN KEY (`idAviso`)
    -- REFERENCES `ava`.`aviso` (`idAviso`)
    -- ON DELETE CASCADE
    -- ON UPDATE CASCADE)
-- ENGINE = InnoDB
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`forum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`forum` (
  `idForum` INT AUTO_INCREMENT NOT NULL COMMENT '',
  `cpfCriador` VARCHAR(14) NOT NULL,
  `titulo` VARCHAR(25) NOT NULL COMMENT '',
  `descricao` TEXT NOT NULL COMMENT '',
  PRIMARY KEY (`idForum`),
    FOREIGN KEY (`cpfCriador`)
    REFERENCES `ava`.`usuario` (`cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`forumusuarioparticipar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`forumUsuarioParticipar` (
  `idForum` INT NOT NULL COMMENT '',
  `cpfUsuario` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`idForum`, `cpfUsuario`),
    FOREIGN KEY (`idForum`)
    REFERENCES `ava`.`forum` (`idForum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `forumusuarioparticipar_ibfk_2`
    FOREIGN KEY (`cpfUsuario`)
    REFERENCES `ava`.`usuario` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`ministraroferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`ministrarOferta` (
  `idOferta` INT NOT NULL COMMENT '',
  `cpfProfessor` VARCHAR(14) NOT NULL,
	PRIMARY KEY (`idOferta`, `cpfProfessor`),
    FOREIGN KEY (`idOferta`)
    REFERENCES `ava`.`ofertaDisciplina` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ministraroferta_ibfk_2`
    FOREIGN KEY (`cpfProfessor`)
    REFERENCES `ava`.`professor` (`cpfProfessor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`materialdisciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`materialDisciplina` (
  `idMaterial` INT AUTO_INCREMENT NOT NULL COMMENT '',
  `idOferta` INT NOT NULL COMMENT '',
  `cpfProfessor` VARCHAR(14) NOT NULL,
  `fonte` VARCHAR(20) NOT NULL COMMENT '',
  `tipo` VARCHAR(20) NOT NULL COMMENT '',
  `arquivo` LONGBLOB NULL DEFAULT NULL COMMENT '',
  
	PRIMARY KEY (`idMaterial`,`idOferta`, `cpfProfessor`),
    FOREIGN KEY (`idOferta` , `cpfProfessor`)
    REFERENCES `ava`.`ministrarOferta` (`idOferta` , `cpfProfessor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`matricular`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`matricular` (
  `cpfAluno` VARCHAR(14) NOT NULL,
  `idOferta` INT NOT NULL COMMENT '',
  `dataMatricula` DATE DEFAULT NULL COMMENT '',
  `numeroProtocolo` VARCHAR(20) NOT NULL COMMENT '',
 
	PRIMARY KEY (`cpfAluno`, `idOferta`),
    FOREIGN KEY (`cpfAluno`)
    REFERENCES `ava`.`aluno` (`cpfAluno`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `matricular_ibfk_2`
    FOREIGN KEY (`idOferta`)
    REFERENCES `ava`.`ofertaDisciplina` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`nota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`nota` (
  `idNota` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `cpfAluno` VARCHAR(14) NOT NULL,
  `idOferta` INT(4) NOT NULL COMMENT '',
  `nota1` DOUBLE DEFAULT NULL COMMENT '',
  `nota2` DOUBLE DEFAULT NULL COMMENT '',
  `nota3` DOUBLE DEFAULT NULL COMMENT '',
  `notaFinal` DOUBLE DEFAULT NULL COMMENT '', -- nota da prova final
  
  PRIMARY KEY (`idNota`),
  CONSTRAINT `nota_ibfk_1`
    FOREIGN KEY (`cpfAluno` , `idOferta`)
    REFERENCES `ava`.`matricular` (`cpfAluno` , `idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`prerequesito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`prerequisito` (
  `dependente` INT NOT NULL COMMENT '',
  `requisito` INT ,
	PRIMARY KEY (`dependente`, `requisito`),
    FOREIGN KEY (`dependente`)
    REFERENCES `ava`.`disciplina` (`idDisciplina`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`realizaratividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`realizarAtividade` (
	`idRealizarAt` INT NOT NULL AUTO_INCREMENT,
	`idAtividade` INT NOT NULL COMMENT '',
	`cpfAluno` VARCHAR(14) NOT NULL,
    `nota` INT(2) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idRealizarAt`,`idAtividade`,`cpfAluno`),
    FOREIGN KEY (`cpfAluno`)
    REFERENCES `ava`.`aluno` (`cpfAluno`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idAtividade`)
    REFERENCES `ava`.`atividade` (`idAtividade`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`tag` (
  `idForum` INT(4) NOT NULL COMMENT '',
  `nome` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`idForum`),
    FOREIGN KEY (`idForum`)
    REFERENCES `ava`.`forum` (`idForum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`solicitacao_projeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`solicitacaoProjeto`(
	`idSolicitacao` INT NOT NULL AUTO_INCREMENT,
    `cpfAluno`		VARCHAR(14) NOT NULL,
    `idProjeto`     INT NOT NULL,
    `estado` 		BOOL DEFAULT NULL,
    PRIMARY KEY(`idSolicitacao`),
    FOREIGN KEY(`cpfAluno`) REFERENCES `ava`.`aluno`(`cpfAluno`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY(`idProjeto`) REFERENCES `ava`.`projetoPesquisa`(`idProjeto`)
	ON DELETE CASCADE
    ON UPDATE CASCADE
);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
