import matplotlib.pyplot as plt
from collections import Counter
import numpy as np
import pandas as pd

# USe LaTeX
# import matplotlib
# matplotlib.rcParams['text.usetex'] = True

with open("data/ex2") as f:
    data = f.readlines()

## Convert to int
data = [int(d) for d in data]

## Old stuff - dont use.
# plt.plot(data)
# plt.savefig("data/ex2-plot.png")
# plt.close()
# plt.hist(data, bins = 32-1) # read docs
# plt.savefig("data/ex2-hist.png")

## Make Histrogram

n = len(data)
maxsize = 16
width = 0.2

count = Counter(data)
vals = [count[i]/n for i in range(maxsize)]
labels = [i for i in range(maxsize)]
indexes = np.arange(len(vals))
theoretical = [0.5*2**(-i) for i in labels]

plt.bar(indexes, vals, width, label = "Experimental Distribution")
plt.xticks(indexes + width * 0.5, labels)
plt.plot(labels, theoretical,"r--", label = "Theoretical Distribution")
plt.title("Distribution of p(h(x))", size = 18)
plt.xlabel("Value",size = 16)
plt.ylabel("Frequency",size = 16)
plt.legend()
plt.savefig("../tex/figs/ex2-hist.png")


### Exercise 4


df = pd.read_csv("../results/results.csv")


n = 1000000
ms = [16,256,1024]
for m in ms:
    d = df[df["m"] == m]
    h = plt.hist(d["Estimation"],bins = 100)

    plt.xlabel("HyperLogLog Estimation",size = 16)
    plt.ylabel("Frequency",size = 16)

    sigma = 1.04/np.sqrt(m)
    plt.title(f"m = {m}",size = 18)



    # normal_distribution = (np.exp(-((x-1000000)^2)/(2*sigma^2)))/(np.sqrt(2*(np.pi)*(sigma^2)))
    a = min(d["Estimation"])
    b = max(d["Estimation"])
    std = np.std(d["Estimation"])
    scale = max(h[0])
    nu = np.mean(d["Estimation"])
    x = np.linspace(a,b,1000)
    y = scale*(np.exp(-((x-1000000)**2)/(2*std**2)))

    plt.axvline(x=1000000, c = "b")
    plt.axvline(x=n*(1+sigma), c = "y")
    plt.axvline(x=n*(1-sigma), c = "y")
    plt.axvline(x=n*(1+2*sigma), c = "r")
    plt.axvline(x=n*(1-2*sigma), c = "r")
    plt.plot(x,y)
    plt.savefig(f"data/ex4{m}.png")
    plt.show()

## Calculate fractions

for m in ms:
    d = df[df["m"] == m]
    sigma = 1.04/np.sqrt(m)
    extimations = d["Estimation"].values

    count_sigma1 = 0
    count_sigma2 = 0
    for i in range(len(extimations)):
        if (n*(1-sigma) < extimations[i]) and (n*(1+sigma) > extimations[i]):
            count_sigma1 += 1
        if (n*(1-2*sigma) < extimations[i]) and (n*(1+2*sigma) > extimations[i]):
            count_sigma2 += 1

    print("m",m)
    print("Fraction 1 sigma",count_sigma1/len(extimations))
    print("Fraction 2 sigma",count_sigma2/len(extimations))


#y
