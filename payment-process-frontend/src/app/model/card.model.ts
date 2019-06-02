export class Card {
    public number: string;
    public holderName: string;
    public expirationMonth: number;
    public expirationYear: number;
    public CVV: number;
    public brand: string;
    public valid: boolean;

    constructor() {
        this.number = "";
        this.holderName = "";
        this.expirationMonth = null;
        this.expirationYear = null;
        this.CVV = null;
        this.brand = "";
        this.valid = false;
    }
}