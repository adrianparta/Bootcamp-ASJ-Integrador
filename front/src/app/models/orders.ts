import { Product } from "./products";

export interface Order{
    id?: number;
    issueDate: Date;
    expectedDeliveryDate: Date;
    info: string;
    supplier: string;
    products: {
        product: Product;
        quantity: number;
    }[];
    total: number;
    status: string;
}