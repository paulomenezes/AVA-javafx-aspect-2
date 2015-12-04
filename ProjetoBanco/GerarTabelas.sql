
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ava
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ava` DEFAULT CHARACTER SET utf8 ;
USE `ava` ;

-- -----------------------------------------------------
-- Table `ava`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`usuario` (

  `idUsuario`	INT AUTO_INCREMENT NOT NULL, 	
  `cpf` VARCHAR(14) NOT NULL UNIQUE ,
  `nome` VARCHAR(40) NOT NULL COMMENT '',
  `foto` LONGBLOB NULL DEFAULT NULL COMMENT '',
  `email` VARCHAR(40) NOT NULL COMMENT '',
  `senha` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`idUsuario`))
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
  `qtdAlunos` INT(3) COMMENT '',
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
  `qtdAlunos` INT(3) NULL COMMENT '',
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
  `idAluno` INT NOT NULL,
  `idCurso` INT(4) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idAluno`),
    FOREIGN KEY (`idAluno`)
    REFERENCES `ava`.`usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idCurso`)
    REFERENCES `ava`.`curso` (`idCurso`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`projetoPesquisa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`projetoPesquisa` (
  `idProjeto` INT AUTO_INCREMENT NOT NULL COMMENT '',
  `nome` VARCHAR(20) NOT NULL COMMENT '',
  `modalidade` VARCHAR(10) NOT NULL COMMENT '',
  `organizacao` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`idProjeto`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`projetoAluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`projetoAluno` (
  `idProjeto` INT NOT NULL COMMENT '',
  `idAluno` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idProjeto`, `idAluno`),
	FOREIGN KEY (`idProjeto`)
    REFERENCES `ava`.`projetoPesquisa`(`idProjeto`),
    FOREIGN KEY (`idAluno`)
    REFERENCES `ava`.`aluno` (`idAluno`)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -------------------------------------------------
-- Table `ava`.projetoProfessor`
-- -------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`projetoProfessor` (
  `idProfessor` INT NOT NULL COMMENT '',
  `idProjeto` INT AUTO_INCREMENT NOT NULL COMMENT '',
  PRIMARY KEY (`idProjeto`, `idProfessor`),
	FOREIGN KEY (`idProjeto`)
    REFERENCES `ava`.`projetoPesquisa`(`idProjeto`),
    FOREIGN KEY (`idProfessor`)
    REFERENCES `ava`.`professor` (`idProfessor`)
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
  PRIMARY KEY (`idArtigo`, `idProjeto`),
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
  `idAviso` INT(11) NOT NULL,
  `titulo` VARCHAR(26) NOT NULL COMMENT '',
  `descricao` TEXT DEFAULT NULL,
  `prioridade` INT(11) NOT NULL COMMENT '',
  `dataEnvio` DATE NULL DEFAULT NULL COMMENT '',
  `horaEnvio` TIME NULL DEFAULT NULL,
  
  PRIMARY KEY (`idAviso`)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`professor` (
  `idProfessor` INT NOT NULL COMMENT '',
  `idDepartamento` INT NOT NULL COMMENT '',
	PRIMARY KEY (`idProfessor`),
    FOREIGN KEY (`idProfessor`)
    REFERENCES `ava`.`usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idDepartamento`)
    REFERENCES `ava`.`departamento` (`idDepartamento`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`coordenador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`coordenador` (
  `idCoordenador` INT NOT NULL,
  `idCurso` INT NOT NULL COMMENT '',
	PRIMARY KEY (`idCoordenador`),
    FOREIGN KEY (`idCoordenador`)
    REFERENCES `ava`.`professor` (`idProfessor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idCurso`)
    REFERENCES `ava`.`curso` (`idCurso`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`diadisciplina`
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
  `idAluno` INT NOT NULL COMMENT '',
   `idOferta` INT NOT NULL,
   `condicao` BOOL,
  `media` FLOAT  DEFAULT NULL COMMENT '',
	PRIMARY KEY (`idAluno`, `idOferta`),
    FOREIGN KEY (`idAluno`)
    REFERENCES `ava`.`aluno` (`idAluno`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idOferta`)
    REFERENCES `ava`.`ofertaDisciplina` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ava`.`disponibaviso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`disponibizarAviso` (
  `idAviso` INT(11) NOT NULL COMMENT '',
  `idUsuario` INT NOT NULL,
  `idOferta` INT NOT NULL COMMENT '',
	PRIMARY KEY (`idUsuario`, `idOferta`, `idAviso`),
    FOREIGN KEY (`idUsuario`)
    REFERENCES `ava`.`usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `disponibaviso_ibfk_3`
    FOREIGN KEY (`idOferta`)
    REFERENCES `ava`.`ofertaDisciplina` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `disponibaviso_ibfk_4`
    FOREIGN KEY (`idAviso`)
    REFERENCES `ava`.`aviso` (`idAviso`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`forum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`forum` (
  `idForum` INT AUTO_INCREMENT NOT NULL COMMENT '',
  `idCriador` INT DEFAULT NULL,
  `titulo` VARCHAR(25) NOT NULL COMMENT '',
  `descricao` TEXT NOT NULL COMMENT '',
  PRIMARY KEY (`idForum`),
    FOREIGN KEY (`idCriador`)
    REFERENCES `ava`.`usuario` (`idUsuario`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`forumusuarioparticipar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`forumUsuarioParticipar` (
  `idForum` INT NOT NULL COMMENT '',
  `idUsuario` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idForum`, `idUsuario`),
    FOREIGN KEY (`idForum`)
    REFERENCES `ava`.`forum` (`idForum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `forumusuarioparticipar_ibfk_2`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `ava`.`usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`ministraroferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`ministrarOferta` (
  `idOferta` INT NOT NULL COMMENT '',
  `idProfessor` INT NOT NULL COMMENT '',
	PRIMARY KEY (`idOferta`, `idProfessor`),
    FOREIGN KEY (`idOferta`)
    REFERENCES `ava`.`ofertaDisciplina` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ministraroferta_ibfk_2`
    FOREIGN KEY (`idProfessor`)
    REFERENCES `ava`.`professor` (`idProfessor`)
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
  `idProfessor` INT NOT NULL COMMENT '',
  `fonte` VARCHAR(20) NOT NULL COMMENT '',
  `tipo` VARCHAR(20) NOT NULL COMMENT '',
  `arquivo` LONGBLOB NULL DEFAULT NULL COMMENT '',
  
	PRIMARY KEY (`idMaterial`,`idOferta`, `idProfessor`),
    FOREIGN KEY (`idOferta` , `idProfessor`)
    REFERENCES `ava`.`ministrarOferta` (`idOferta` , `idProfessor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`matricular`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`matricular` (
  `idAluno` INT NOT NULL COMMENT '',
  `idOferta` INT NOT NULL COMMENT '',
  `dataMatricula` DATE DEFAULT NULL COMMENT '',
  `numeroProtocolo` VARCHAR(20) NOT NULL COMMENT '',
 
	PRIMARY KEY (`idAluno`, `idOferta`),
    FOREIGN KEY (`idAluno`)
    REFERENCES `ava`.`aluno` (`idAluno`)
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
  `idAluno` INT NOT NULL COMMENT '',
  `idOferta` INT(4) NOT NULL COMMENT '',
  `nota1` INT(2) NOT NULL COMMENT '',
  `nota2` INT(2) NOT NULL COMMENT '',
  `nota3` INT(2) NULL COMMENT '',
  `notaFinal` INT(2) NULL COMMENT '', -- mediaFinal
  
  PRIMARY KEY (`idNota`,`idAluno`,`idOferta`),
  CONSTRAINT `nota_ibfk_1`
    FOREIGN KEY (`idAluno` , `idOferta`)
    REFERENCES `ava`.`matricular` (`idAluno` , `idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`participarprojeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`participarProjeto` (
  `idProjeto` INT NOT NULL COMMENT '',
  `idAluno` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idProjeto`, `idAluno`),
    FOREIGN KEY (`idProjeto`)
    REFERENCES `ava`.`projetoPesquisa` (`idProjeto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `participarprojeto_ibfk_2`
    FOREIGN KEY (`idAluno`)
    REFERENCES `ava`.`aluno` (`idAluno`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ava`.`prerequesito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ava`.`prerequisito` (
  `idCurso` INT NOT NULL COMMENT '',
  `dependente` INT NOT NULL COMMENT '',
  `requisito` INT(4)  COMMENT '',
	PRIMARY KEY (`idCurso`,`dependente`, `requisito`),
    FOREIGN KEY (`dependente`)
    REFERENCES `ava`.`disciplina` (`idDisciplina`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`requisito`)
    REFERENCES `ava`.`disciplina` (`idDisciplina`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idCurso`)
    REFERENCES `ava`.`curso` (`idCurso`)
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
	`idAluno` INT NOT NULL COMMENT '',
    `nota` INT(2) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idRealizarAt`,`idAtividade`,`idAluno`),
    FOREIGN KEY (`idAluno`)
    REFERENCES `ava`.`aluno` (`idAluno`)
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
    `idAluno`		  INT NOT NULL,
    `idProjeto`     INT NOT NULL,
    `estado` 		  BOOL NOT NULL,
    PRIMARY KEY(`idSolicitacao`,`idAluno`,`idProjeto`),
    FOREIGN KEY(`idAluno`) REFERENCES `ava`.`aluno`(`idAluno`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY(`idProjeto`) REFERENCES `ava`.`projetoPesquisa`(`idProjeto`)
	ON DELETE CASCADE
    ON UPDATE CASCADE
);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
