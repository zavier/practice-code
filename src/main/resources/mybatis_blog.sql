DROP TABLE IF EXISTS `t_blog`;

CREATE TABLE `t_blog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_blog` WRITE;
/*!40000 ALTER TABLE `t_blog` DISABLE KEYS */;

INSERT INTO `t_blog` (`id`, `title`, `content`)
VALUES
	(1,'First Title','this is content');

/*!40000 ALTER TABLE `t_blog` ENABLE KEYS */;
UNLOCK TABLES;