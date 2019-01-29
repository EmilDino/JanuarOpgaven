# JanuarOpgaven
Spiludvikling
Dette er et platformerspil, i stil med andre spil som f.eks. Mario og ligende.
Det er bygget med fxgl version 0.4.0. og Tiled.
Tiled er brugt til at bygge det level i spillet som spilleren bevæger sig rundt i.
Via Tiled har jeg lavet et level som er blevet eksporteret som json fil, ind i mit spil.
json filen virker som blueprint til mit mit level, og det level bliver bygget af PlatformerFactory klassen.
Spilleren bliver styret med WASD tasterne, hvor at W er hop, A bevæger sig til venste, D til højre og med S dykker man, hvis man er i luften. 
Spillerkarakteren er sat som en PhysicsComponent, hvliket vil sige at den er påvirket af tyndekraft og stopper når den kollider med platformende.
