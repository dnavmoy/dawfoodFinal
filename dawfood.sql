
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dawfood`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--
DROP DATABASE if exists DawFood ;
CREATE DATABASE DawFood;

USE DawFood;

CREATE TABLE `categorias` (
  `CodCategoria` int(11) NOT NULL,
  `NombreCategoria` varchar(100) NOT NULL,
  `tipo` enum('comida','bebida','postre') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`CodCategoria`, `NombreCategoria`, `tipo`) VALUES
(1, 'primero', 'comida'),
(2, 'segundo', 'comida'),
(3, 'entrante', 'comida'),
(4, 'con gas', 'bebida'),
(5, 'sin gas', 'bebida'),
(6, 'alcoholico', 'bebida'),
(7, 'frio', 'postre'),
(8, 'caliente', 'postre'),
(9, 'fruta', 'postre');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `IdProducto` int(11) NOT NULL,
  `Descripcion` varchar(300) NOT NULL,
  `Precio` decimal NOT NULL,
  `Iva` float NOT NULL,
  `Stock` int(11) NOT NULL,
  `CodCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`IdProducto`, `Descripcion`, `Precio`, `Iva`, `Stock`, `CodCategoria`) VALUES
(1, 'Ensalada', 7, '10', 10, 3),
(2, 'gazpacho', 5, '10', 5, 3),
(3, 'albondigas', 12, '10', 20, 1),
(4, 'arroz caldoso', 15, '10', 8, 1),
(5, 'tarta de manzana', 4, '10', 12, 7),
(6, 'cerveza', 2, '21', 54, 6);





-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productosticket`
--

CREATE TABLE `productosticket` (
  `IdProducto` int(11) NOT NULL,
  `IdTicket` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;


INSERT INTO productosticket 
	(IdProducto,IdTicket,Cantidad) 
VALUES
	(1,1,1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

CREATE TABLE `ticket` (
  `IdTicket` int(11) NOT NULL,
  `NumPedido` int(11) NOT NULL,
  `CodTransaccion` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `TotalPedido` double NOT NULL,
  `TotalIva` double NOT NULL,
  `CodigoTpv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;


insert INTO ticket (IdTicket,NumPedido,codTransaccion,Fecha,TotalPedido,TotalIva,CodigoTpv)
VALUES(1,0,1,"2024-05-21",25,3,1);
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tpv`
--

CREATE TABLE `tpv` (
  `CodigoTpv` int(11) NOT NULL,
  `Ubicacion` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tpv`
--

INSERT INTO `tpv` (`CodigoTpv`, `Ubicacion`) VALUES
(1, 'calle terraza, estepona');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`CodCategoria`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`IdProducto`),
  ADD KEY `fk_categoria` (`CodCategoria`);

--
-- Indices de la tabla `productosticket`
--
ALTER TABLE `productosticket`
  ADD PRIMARY KEY (`IdProducto`,`IdTicket`),
  ADD KEY `fk_ticket` (`IdTicket`);

--
-- Indices de la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`IdTicket`),
  ADD KEY `fk_tpv` (`CodigoTpv`);

--
-- Indices de la tabla `tpv`
--
ALTER TABLE `tpv`
  ADD PRIMARY KEY (`CodigoTpv`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `CodCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `IdProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ticket`
--
ALTER TABLE `ticket`
  MODIFY `IdTicket` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tpv`
--
ALTER TABLE `tpv`
  MODIFY `CodigoTpv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_categoria` FOREIGN KEY (`CodCategoria`) REFERENCES `categorias` (`CodCategoria`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `productosticket`
--
ALTER TABLE `productosticket`
  ADD CONSTRAINT `fk_producto` FOREIGN KEY (`IdProducto`) REFERENCES `producto` (`IdProducto`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ticket` FOREIGN KEY (`IdTicket`) REFERENCES `ticket` (`IdTicket`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `fk_tpv` FOREIGN KEY (`CodigoTpv`) REFERENCES `tpv` (`CodigoTpv`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;





/*!40101 SET CHAcategoriasCodCategoriaNombreCategoriaIdProductoRACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
