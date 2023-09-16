export interface SignalStrengthType {
  dbm: number;
  asuLevel: number;
  level: number;
}

export interface SignalStrengthCdma extends SignalStrengthType {
  cdmaDbm: number;
  cdmaEcio: number;
  cdmaLevel: number;
  evdoDbm: number;
  evdoEcio: number;
  evdoLevel: number;
  evdoSnr: number;
}

export interface SignalStrengthGsm extends SignalStrengthType {
  bitErrorRate: number;
  rssi: number;
  timingAdvance: number;
}

export interface SignalStrengthLte extends SignalStrengthType {
  cqi: number;
  cqiTableIndex: number;
  rsrp: number;
  rsrq: number;
  rssi: number;
  rssnr: number;
  timingAdvance: number;
}

export interface SignalStrengthNr extends SignalStrengthType {
  csiCqiReport: number[];
  csiCqiTableIndex: number;
  csiRsrp: number;
  csiRsrq: number;
  csiSinr: number;
  ssRsrp: number;
  ssRsrq: number;
  ssSinr: number;
}

export interface SignalStrengthTdscdma extends SignalStrengthType {
  rscp: number;
}

export interface SignalStrengthWcdma extends SignalStrengthType {
  ecNo: number;
}

export type SignalStrength =
  | SignalStrengthWcdma
  | SignalStrengthTdscdma
  | SignalStrengthNr
  | SignalStrengthLte
  | SignalStrengthGsm
  | SignalStrengthCdma;
