export class Call {
  id: number;
}

export enum CallStatus {
  COMPLETED = 4,
  BUSY = 5,
  FAILED = 6,
  NO_ANSWER = 7,
  CANCELED = 8
}
