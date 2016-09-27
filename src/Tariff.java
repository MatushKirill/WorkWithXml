/**
 * Created by Кирилл on 05.04.2016.
 */
public class Tariff {
    String id;
    String name;
    String operatorName;
    int payroll;
    CallPrice callPrice;
    Parameters paraeters;
    static class CallPrice{
        double withinTheNetwork;
        double OutOfTheNetwork;
        double OntheLandlinPrice;

        public double getWithinTheNetwork() {
            return withinTheNetwork;
        }

        public void setWithinTheNetwork(double withinTheNetwork) {
            this.withinTheNetwork = withinTheNetwork;
        }

        public double getOutOfTheNetwork() {
            return OutOfTheNetwork;
        }

        public void setOutOfTheNetwork(double outOfTheNetwork) {
            OutOfTheNetwork = outOfTheNetwork;
        }

        public double getOntheLandlinPrice() {
            return OntheLandlinPrice;
        }

        public void setOntheLandlinPrice(double ontheLandlinPrice) {
            OntheLandlinPrice = ontheLandlinPrice;
        }
    }
    static class Parameters{
        public int getConnectinFree() {
            return connectinFree;
        }

        public void setConnectinFree(int connectinFree) {
            this.connectinFree = connectinFree;
        }

        public String getTarification() {
            return tarification;
        }

        public void setTarification(String tarification) {
            this.tarification = tarification;
        }

        int connectinFree;
        String tarification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getPayroll() {
        return payroll;
    }

    public void setPayroll(int payroll) {
        this.payroll = payroll;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = callPrice;
    }

    public Parameters getParaeters() {
        return paraeters;
    }

    public void setParaeters(Parameters paraeters) {
        this.paraeters = paraeters;
    }
}
