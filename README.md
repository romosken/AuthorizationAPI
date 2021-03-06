# AuthorizationAPI
## API de autorização para o coding challenge da Let's Code

### Status: Versão 1.0 finalizada!


## Sobre

Desenvolvedor: Rodrigo Ricci de Paula Mosken


 O projeto foi desenvolvido utilizando minha concepção de Clean Arch, 
 usando seu famoso "tripé" Adapters, Usecases, Entities. 
 A API concentra a funcionalidade de login e autenticação de usuário, 
 utiliza um banco de dados mySQL, o mesmo da API CineCriticas, para conferência 
de dados do usuário, e valida JWTs (Json Web Token).

Possui dois endpoints, que serão descritos no próximo tópico!

## Endpoints

1) POST authorization/v1/login
   - Headers : 
        1) String username (id do usuário);
        2) String password (senha do usuário).
   - Retorno:
        1) codigo 200 OK: 
     ````
     {
           "token": "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOiJGcmkgSnVsIDAxIDIxOjQ0OjM1IEJSVCAyMDIyIiwiaWF0IjoiRnJpIEp1bCAwMSAyMDo0NDozNSBCUlQgMjAyMiIsInN1YiI6InJvbW9za2VuIiwianRpIjoiOTQ1YzIwYzYtNGRmMC00NjFjLWJkZTctNjFhNzg1MGRkYzBiIn0.A4LESSHwRcznCQ4yFrOBMB40KkKzRP3HzaSIIRecraY"
     }
     ````
        2) codigo 401 Unauthorized: 
     ````
     {
       "timestamp": "01-07-2022 11:45:57",
       "code": 401,
       "status": "UNAUTHORIZED",
       "message": "Username/email or password invalid!"
     }
       ````   

Ao bater no endpoint de login fornecendo username e senha, a API valida as informações
         encripta a senha e confere com as informações contidas no banco de dados, se tudo bater
         , gera-se um JWT e é retornado ao usuário.

2) GET authorization/v1/token/parse
   - Headers :
     1) String token (JWT gerado anteriormente para o usuário);

   - Retorno:
       1) codigo 200 OK: 
     ````
     {
       "subject": "romosken",
       "id": "7fcce8f4-a05f-427b-a2b0-8dc742995338",
       "issued_at": "+54466-01-11T03:16:43.000+00:00",
       "expiration_date": "+54466-02-21T19:16:43.000+00:00"
     }
     ````
     
       2) codigo 400 BAD REQUEST: 
     ````
     {
       "timestamp": "01-07-2022 11:48:38",
       "code": 400,
       "status": "BAD_REQUEST",
       "message": "Token expired or invalid!"
     }
     ````

Ao bater no endpoint de parse fornecendo token, a API tenta desencriptar o token, caso haja
falha, o token é retornado como invalido, caso sucesso, são retornadas as informações contidas no token,
como data de expiração, e usuário dono do token.

## Tecnologias Utilizadas
- JDK 11
- Spring
- Maven
- MySQL
- IntelliJ IDE

## Rodando o Projeto
- Para executar o projeto é necessário ter o banco de dados MySQL rodando, abaixo segue a query para criação do banco:
  (O arquivo contendo o código abaixo pode ser encontrado na pasta "documents" na raiz do projeto com o nome "createdatabase.sql")
 ```
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinecriticas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinecriticas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinecriticas` ;
USE `cinecriticas` ;

-- -----------------------------------------------------
-- Table `cinecriticas`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`Role` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinecriticas`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`User` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `xp` INT NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_Users_Roles_idx` (`role_name` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role`
    FOREIGN KEY (`role_name`)
    REFERENCES `cinecriticas`.`Role` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinecriticas`.`Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`Rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `rating` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Rating_User_idx` (`username` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_Rating_User`
    FOREIGN KEY (`username`)
    REFERENCES `cinecriticas`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinecriticas`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`Comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `text` MEDIUMTEXT NOT NULL,
  `likes` INT NULL,
  `dislikes` INT NULL,
  `comment_reference` INT NULL,
  `comment_reply` INT NULL,
  `repeated` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Comment_User_idx` (`username` ASC) VISIBLE,
  INDEX `fk_Comment_Reference_idx` (`comment_reference` ASC) VISIBLE,
  INDEX `fk_Comment_Reply_idx` (`comment_reply` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_User`
    FOREIGN KEY (`username`)
    REFERENCES `cinecriticas`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Reference`
    FOREIGN KEY (`comment_reference`)
    REFERENCES `cinecriticas`.`Comment` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Reply`
    FOREIGN KEY (`comment_reply`)
    REFERENCES `cinecriticas`.`Comment` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `cinecriticas`.`role`
(`name`)
VALUES
("LEITOR"),
("BASICO"),
("AVANCADO"),
("MODERADOR");
```

- Após criação do banco alterar o parametro spring.datasourse.url no arquivo application.yml
com o endereço do seu banco de dados, e os outros parametros conforme seu ambiente/vontade.


- Após isso você tem tudo que é necessário para executar a API! Os testes podem ser realizados via Postman ou similares
ou pela API CineCriticas, que utiliza esses dois endpoints internamente!


- A Collection do Postman se encontra na pasta "documents" na raiz do projeto, com o nome "Desafio Let's Code.postman_collection.json"!


- O diagrama do modelo do banco de dados se encontra na pasta "documents" na raiz do projeto, com o nome "databaseEERModel.png"
e em formato do MySQL Workbench com o nome "model.mwd"
