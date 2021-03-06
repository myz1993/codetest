export interface SummaryDto {
  currency: string;
  price: number;
  day: number;
  week: number;
  volume: number;
  marketCap: number;
}

export interface LabelDto {
  id: string;
  large: string;
  market_cap_rank: number;
  name: string;
  symbol: string;
  thumb: string;
}

export interface TableData {
  currency: string;
  price: number;
  day: number;
  week: number;
  volume: number;
  marketCap: number;
  label: string;
  thumb: string;
  rank: number;
}
