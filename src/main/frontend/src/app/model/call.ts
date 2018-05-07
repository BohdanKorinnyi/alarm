export class Call {
  id: number;
  parentId: number;
  cost: number;
  creation: string;
  updated: string;
  duration: number;
  fullyListened: boolean;
  callNumber: CallNumber;
  alarm: Alarm;
  callStatus: Status;
}

export class Status {
  id: number;
  name: string;
}

export class CallNumber {
  id: number;
  number: string;
  creation: string;
  active: boolean;
}

export class Alarm {
  id: number;
  language: Language;
}

export class Language {
  id: number;
  code: string;
  name: string;
  intro: string;
}

export enum CallStatus {
  COMPLETED = 4,
  BUSY = 5,
  FAILED = 6,
  NO_ANSWER = 7,
  CANCELED = 8
}
