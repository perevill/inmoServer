-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 16-02-2022 a las 23:18:59
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inmobiliaria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`id`, `nombre`) VALUES
(1, 'Valencia'),
(2, 'Castellón'),
(3, 'Alicante'),
(5, 'Barcelona'),
(9, 'Lérida'),
(10, 'Sevilla'),
(12, 'Madrid'),
(13, 'Murcia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comarca`
--

CREATE TABLE `comarca` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idciudad` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `comarca`
--

INSERT INTO `comarca` (`id`, `nombre`, `idciudad`) VALUES
(1, 'Quatre Carreres', 1),
(2, 'Malillaaa', 1),
(4, 'Ribera baja', 1),
(5, 'Vega baja', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos`
--

CREATE TABLE `favoritos` (
  `id` bigint(20) NOT NULL,
  `idvivienda` bigint(11) NOT NULL,
  `idusuario` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `file`
--

CREATE TABLE `file` (
  `id` bigint(20) NOT NULL,
  `file` blob NOT NULL,
  `name` varchar(250) NOT NULL,
  `type` varchar(250) NOT NULL,
  `idvivienda` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `nombre`) VALUES
(1, 'admin'),
(2, 'normal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipovivienda`
--

CREATE TABLE `tipovivienda` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipovivienda`
--

INSERT INTO `tipovivienda` (`id`, `nombre`) VALUES
(1, 'Apartamento'),
(2, 'Chalet');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `apellido1` varchar(45) NOT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `tlf` varchar(45) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `codpostal` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `profesional` tinyint(1) NOT NULL,
  `email` varchar(45) NOT NULL,
  `idtipousuario` bigint(20) NOT NULL,
  `validado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `login`, `password`, `apellido1`, `apellido2`, `tlf`, `dni`, `codpostal`, `direccion`, `profesional`, `email`, `idtipousuario`, `validado`) VALUES
(1, 'Pere', 'peree', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'Villanueva', NULL, '6666666666', '24585439C', '46026', 'Carrera Malilla 45', 1, 'correo@inmo.com', 1, 1),
(2, 'Daniel', 'daniel', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'Navarro', 'Navarro', '6666666666', '24585439C', '46026', 'Carrera Malilla', 0, 'correo@inmo.com', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vivienda`
--

CREATE TABLE `vivienda` (
  `id` bigint(11) NOT NULL,
  `ubicacion` varchar(45) NOT NULL,
  `plano` varchar(45) DEFAULT NULL,
  `precio` double NOT NULL,
  `m2utiles` double NOT NULL,
  `m2construidos` double NOT NULL,
  `habitaciones` int(11) NOT NULL,
  `antiguedad` int(11) DEFAULT NULL,
  `nplanta` int(11) DEFAULT NULL,
  `exterior` tinyint(1) DEFAULT NULL,
  `conservacion` varchar(45) DEFAULT NULL,
  `descripcion` varchar(255) NOT NULL,
  `idanunciante` bigint(20) NOT NULL,
  `idzona` bigint(20) NOT NULL,
  `idtipovivienda` bigint(20) NOT NULL,
  `comprar` tinyint(1) NOT NULL,
  `alquilar` tinyint(1) NOT NULL,
  `obranueva` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vivienda`
--

INSERT INTO `vivienda` (`id`, `ubicacion`, `plano`, `precio`, `m2utiles`, `m2construidos`, `habitaciones`, `antiguedad`, `nplanta`, `exterior`, `conservacion`, `descripcion`, `idanunciante`, `idzona`, `idtipovivienda`, `comprar`, `alquilar`, `obranueva`) VALUES
(1, 'Calle Murla 4', NULL, 25000, 50, 57, 3, 10, 3, 1, 'Obra nueva', 'PISO AMPLIO CON VISTAS AL PARQUE CENTRAL AIRE ACONDICIONADO BUENA COMUNIDAD Y ASCENSOR', 1, 1, 1, 1, 0, 0),
(2, 'Calle Antonio Sequeros 53', NULL, 567, 127, 142, 2, 16, 4, 1, 'Necesita reforma', 'CERCA DEL CENTRO AIRE ACONDICIONADO MUY BUENAS VISTAS A LA CALLE CERCA DE SUPERMERCADO Y TIENDA DE ANIMALES', 2, 5, 1, 0, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `zona`
--

CREATE TABLE `zona` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `codpostal` varchar(45) NOT NULL,
  `idcomarca` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `zona`
--

INSERT INTO `zona` (`id`, `nombre`, `codpostal`, `idcomarca`) VALUES
(1, 'Malilla', '46006', 1),
(2, 'Cullera', '46404', 1),
(3, 'Sueca', '46410', 2),
(5, 'Almoradí', '03160', 5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comarca`
--
ALTER TABLE `comarca`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipovivienda`
--
ALTER TABLE `tipovivienda`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `vivienda`
--
ALTER TABLE `vivienda`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `zona`
--
ALTER TABLE `zona`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `comarca`
--
ALTER TABLE `comarca`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `file`
--
ALTER TABLE `file`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipovivienda`
--
ALTER TABLE `tipovivienda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `vivienda`
--
ALTER TABLE `vivienda`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `zona`
--
ALTER TABLE `zona`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
