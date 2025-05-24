-- Test 1: Datos para 14 de junio a las 10:00
INSERT INTO PRICES (PRICE, BRAND_ID, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, START_DATE, CURR)
VALUES (
    35.50,
    1,
    PARSEDATETIME('2020-06-14 23:59:59', 'yyyy-MM-dd HH:mm:ss'),
    1,
    35455,
    0,
    PARSEDATETIME('2020-06-14 00:00:00', 'yyyy-MM-dd HH:mm:ss'),
    'EUR'
);

-- Test 2: Datos para 14 de junio a las 16:00 (mayor prioridad)
INSERT INTO PRICES (PRICE, BRAND_ID, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, START_DATE, CURR)
VALUES (
    25.45,
    1,
    PARSEDATETIME('2020-06-14 18:30:00', 'yyyy-MM-dd HH:mm:ss'),
    2,
    35455,
    1,
    PARSEDATETIME('2020-06-14 15:00:00', 'yyyy-MM-dd HH:mm:ss'),
    'EUR'
);

-- Test 3: Datos para 14 de junio a las 21:00
INSERT INTO PRICES (PRICE, BRAND_ID, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, START_DATE, CURR)
VALUES (
    35.50,
    1,
    PARSEDATETIME('2020-06-15 00:00:00', 'yyyy-MM-dd HH:mm:ss'),
    1,
    35455,
    0,
    PARSEDATETIME('2020-06-14 18:31:00', 'yyyy-MM-dd HH:mm:ss'),
    'EUR'
);

-- Test 4: Datos para 15 de junio a las 10:00
INSERT INTO PRICES (PRICE, BRAND_ID, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, START_DATE, CURR)
VALUES (
    30.50,
    1,
    PARSEDATETIME('2020-06-15 11:00:00', 'yyyy-MM-dd HH:mm:ss'),
    3,
    35455,
    1,
    PARSEDATETIME('2020-06-15 00:00:00', 'yyyy-MM-dd HH:mm:ss'),
    'EUR'
);

-- Test 5: Datos para 16 de junio a las 21:00
INSERT INTO PRICES (PRICE, BRAND_ID, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, START_DATE, CURR)
VALUES (
    38.95,
    1,
    PARSEDATETIME('2020-12-31 23:59:59', 'yyyy-MM-dd HH:mm:ss'),
    4,
    35455,
    1,
    PARSEDATETIME('2020-06-15 16:00:00', 'yyyy-MM-dd HH:mm:ss'),
    'EUR'
);
