# Hexapawn — Roadmap

A 3×3 Hexapawn game (Martin Gardner's "HER" variant) with a MENACE-style self-learning
opponent. Built as a Java fluency rebuild: primitives → Spring Boot → persistence → frontend.

Time markers are *focused-work* estimates, not calendar time. `~Xh` = hours, `~Xd` = days.
Quick-win vs. grind is called out per phase.

---

## P0 — Hexapawn core (plain Java, terminal) · **priority phase** · ~6–10h
Pure primitives. No frameworks, no persistence — just classes and control flow.

- [ ] Maven skeleton: `pom.xml`, `src/main/java/com/cpabad/hexapawn/`, `src/test/java/...`
- [ ] First commit + push to `origin` (hexapawn-java) — skeleton before features
- [ ] `Board` — 3×3 state, render to terminal
- [ ] `Move` — from/to squares in a1–c3 algebraic notation
- [ ] Legal-move generation (pawn advance, diagonal capture; no double-step in Hexapawn)
- [ ] Win detection (reach far rank · capture all · opponent has no legal move)
- [ ] `Game` loop — human vs. human, input parsed as a1–c3
- **Quick win:** Board + render. **Grind:** legal-move generation + the three win conditions.

## P1 — Learning opponent (MENACE / HER) · ~8–14h
- [ ] In-memory state store: board state → per-move weights
- [ ] Weighted random move selection
- [ ] Reinforce winning moves / penalize losing moves after each game
- [ ] Training loop (self-play or vs. random) until convergence (~24 positions folded)
- [ ] Symmetry-folding — *optional*, do it only if convergence is too slow without it
- [ ] **Cucumber BDD loop starts here**
- **Quick win:** in-memory store + weighted pick. **Grind:** the reinforce/penalize tuning.

## P2 — Spring Boot REST + auth + leaderboard + anti-cheat · ~3–5d
- [ ] Controller → Service → Repository layering
- [ ] Login
- [ ] Leaderboard
- [ ] Move-log anti-cheat: replay the a1–c3 log server-side to validate legality
- [ ] **Database enters here** — clean 3NF schema: `users`, `games`, `moves`, `state_weights`
- **Quick win:** one GET endpoint end-to-end. **Grind:** auth + the layered refactor.

## P2.5 — Harden 3NF → 5NF · ~3–6h
- [ ] Find a real join-dependency in the data and decompose to 5NF (driven by data, not theory)

## P3 — Frontend (my hands) · ~4–7d
- [ ] vanilla JS → vanilla TS → Angular, in that order

## P4 — Stretch / open thread (much later) · placeholder
- [ ] Knights variant on ≥5×5 board where the tabular table no longer transfers →
      minimax / feature-based weights. Resolved well after the core game works.

## Parallel track — Auth microservice + Terraform (IaC) · **gated: begins after P0** · ~TBD
A deliberately minimal Spring Boot + JWT login-auth service (issues/validates tokens that
Hexapawn and later apps can call), used as a **realistic workload for hands-on Terraform
authoring**. The learning target is Terraform, not the auth service. Provisioned and
orchestrated **locally only** — LocalStack (emulated AWS) and/or the Terraform Docker
provider. **No live cloud spend.** Peer-coder mode applies to both the Java *and* the HCL.

Decisions deferred until the track starts (recommend / I decide):
- Repo structure: own repo vs. sibling folder beside Hexapawn.
- Terraform target: Docker provider vs. LocalStack vs. a progression.

- [ ] (gated on P0) Walk-through for my decision: (1) repo structure, (2) TF target env,
      (3) step-by-step TF learning path ending with TF orchestrating the auth service
      locally, (4) smallest auth-service design that's still a real TF workload.
- [ ] Then I author all HCL + Java by hand.

---

### Parallel (tracked outside this repo)
Daily LeetCode-in-Java + concept/SQL recall drills. Context, not a roadmap phase.

### Learning objectives
1. Java fluency from primitives up (P0 priority) · 2. Spring Boot REST + JPA + layering (P2)
3. Relational design 3NF→5NF (P2/P2.5) · 4. Testing: Cucumber + JUnit/Mockito · 5. FE JS→TS→Angular (P3)
