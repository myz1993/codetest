CREATE TABLE IF NOT EXISTS "crypto_historical_data" (
    "id" serial PRIMARY KEY,
    "currency" TEXT,
    "date" TEXT,
    "open" NUMERIC(10, 6),
    "high" NUMERIC(11, 6),
    "low" NUMERIC(10, 6),
    "close" NUMERIC(10, 6),
    "volume" BIGINT,
    "market_cap" BIGINT
);