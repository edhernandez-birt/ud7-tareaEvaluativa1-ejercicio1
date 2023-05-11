---No me funciona creando el esquema desde aquí. Creo el esquema en mySQL o en cadena de conexion de aplication.properties
---CREATE SCHEMA IF NOT EXISTS `news` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `news`;

CREATE TABLE IF NOT EXISTS `news`.`noticia` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `fecha_noticia` date DEFAULT NULL,
  `texto` longtext,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `SYS_C008274` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `news`.`usuario` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `SYS_C008276` (`id`),
  UNIQUE KEY `UK_USUARIO_EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `news`.`noticia` (`id`, `fecha_noticia`, `titulo`,`texto`) 
VALUES ('1', STR_TO_DATE('05/03/23', '%d/%m/%y'), 'Noticia larga de prueba','Texto de la primera noticia para probar');

INSERT INTO `news`.`usuario` (`id`, `apellidos`, `email`, `nombre`) 
VALUES ('1', 'Hernandez Garcia', 'txefoedu@hotmail.com', 'Eduardo');

CREATE TABLE IF NOT EXISTS `news`.`comentario`
(
    `id` bigint(19) NOT NULL AUTO_INCREMENT,
    `fecha_comentario` date,
    `texto` varchar(255),
    `noticia_id` bigint(19),
    `usuario_id` bigint(19),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_comentario_noticia` FOREIGN KEY (`noticia_id`)
        REFERENCES `news`.`noticia`(`id`),
    CONSTRAINT `fk_comentario_usuario` FOREIGN KEY (`usuario_id`)
        REFERENCES `news`.`usuario`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- INSERTING into NOTICIA.COMENTARIO
INSERT INTO `news`.`comentario`(`id`,`fecha_comentario`,`texto`,`noticia_id`,`usuario_id`)
VALUES (1,'2023-03-05','Parece que va bien la noticia larga',1,1);

-- DDL for Index SYS_C008272
CREATE UNIQUE INDEX `sys_c008272` ON `news`.`comentario` (`id`);

INSERT INTO `news`.`noticia` (id,titulo,texto,fecha_Noticia) values (2,'Empate de la SD Eibar','El Eibar solo ha conseguido un empate a uno en su visita a Granada','2023-4-30');
INSERT INTO `news`.`noticia` (id,titulo,texto,fecha_Noticia) values (3,'Fernando Alonso cuarto en Baku','Fernando Alonso se ha quedado a las puertas del podio en el último GP','2023-4-30')