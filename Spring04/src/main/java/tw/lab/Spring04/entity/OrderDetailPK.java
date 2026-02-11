package tw.lab.Spring04.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailPK implements Serializable {
	private int orderId;
	private int productId;

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof OrderDetailPK))
			return false;
		OrderDetailPK that = (OrderDetailPK) obj;

		return this.orderId == that.orderId
				&&
				this.productId == that.productId;
	}

}
