# Contributing to Habit Tracker

This applies to both the backend and frontend repos. Add this section (or link to a shared `CONTRIBUTING.md`) in each project's README.

## Branching Strategy

We use a simplified two-branch model:

- **`main`** — always stable and deployable. No direct commits.
- **`develop`** — integration/staging branch. All feature work merges here first.
- **`feature/MAP-XXX-short-description`** — one branch per Trello ticket, branched off `develop`.

`develop` is periodically merged into `main` once a batch of work is stable and tested.

## Getting Started

```bash
# Clone the repo
git clone <repo-url>
cd <repo-name>

# Switch to develop and make sure it's up to date
git checkout develop
git pull origin develop
```

## Working on a Ticket

1. Pick up a ticket from the Trello board (e.g. `MAP-013`) and move it to **In Progress**.
2. Create a feature branch off `develop`, named after the ticket:
   ```bash
   git checkout -b feature/MAP-013-create-habit-endpoint
   ```
3. Make your changes, committing as you go (see commit message format below).
4. Push your branch and open a Pull Request targeting `develop` — **not** `main`:
   ```bash
   git push -u origin feature/MAP-013-create-habit-endpoint
   ```
5. In the PR description, link the ticket (e.g. `Closes MAP-013` or just reference the ticket code) and briefly describe what changed.
6. Move the Trello card to **Code Review** and request a review.
7. Once approved, merge the PR into `develop` (squash merge preferred, to keep history clean) and move the card to **Done**.

## Commit Message Format

Use [Conventional Commits](https://www.conventionalcommits.org/) style:

```
<type>: <short summary>

<optional longer description>
```

Common types: `feat` (new feature), `fix` (bug fix), `docs` (documentation only), `refactor`, `test`, `chore` (tooling/config).

Examples:
```
feat: add POST /users endpoint with validation

fix: prevent duplicate habit logs on the same date

docs: update README with local setup steps
```

Reference the ticket code in the body when helpful, e.g. `Refs MAP-013`.

## Pull Request Guidelines

- Keep PRs scoped to a single ticket where possible — easier to review, easier to revert if something breaks.
- Write a short description of *what* changed and *why*, not just *what files* changed.
- Make sure your branch is up to date with `develop` before requesting review:
  ```bash
  git checkout develop
  git pull origin develop
  git checkout feature/MAP-013-create-habit-endpoint
  git merge develop
  ```
- At least one approval is required before merging (branch protection is enabled on `develop` and `main`).

## Code Review Expectations

- Reviewers should check: does it meet the ticket's acceptance criteria, is there reasonable test coverage (backend), does it follow existing code style?
- Be constructive — this is a learning project. Explain *why* something should change, not just *that* it should.
- If you're the one being reviewed, don't take feedback personally — ask questions if a comment isn't clear.

## Questions

Post in the relevant Slack channel (`#habit-tracker-backend`, `#habit-tracker-frontend`, or `#habit-tracker-devops`) rather than DMing individuals, so answers are visible to the whole cohort.